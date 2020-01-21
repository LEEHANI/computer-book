
# 3장 SQL의 조건 분기
:구문에서 식으로 
## 8강 UNION을 사용한 쓸데없이 긴 표현 
- UNION을 사용한 조건 분기는 SQL 초보자가 좋아하는 기술 중 하나이다.
- 일반적으로 이러한 조건 분기는, WHEE 구만 조금씩 다른 여러 개의 SELECT 구문을 합쳐서, 복수의 조건에 일치하는 하나의 결과 집합을 얻고 싶을 때 사용한다. 
- 하지만 이런 방법은 성능적인 측면에서 굉장히 큰 단점을 갖고있다. 
- 외부적으로는 하나의 SQL 구문을 실행하는 것처럼 보이지만, 내부적으로는 여러 개의 SELECT 구문을 실행하는 실행 계획으로 해석되기 때문이다.
- 따라서 테이블에 접근하는 횟수가 많아져서 I/O 비용이 크게 늘어난다. 
### 1. UNION을 사용한 조건 분기와 관련된 간단한 예제
- 2001년 까지는 세금이 포함되지 않은 가격을, 2002년부터는 세금이 포함된 가격을 'price' 필드로 표시하여라
```sql
SELECT item_name, year, price_tax_ex AS price
FROM items
WHERE year <= 2001
UNION ALL 
SELECT item_name, year, price_tax_ex AS price 
FROM items
WHERE year >= 2002
;
```
- UNION을 사용했을 때의 실행 계획 문제
  + 쓸데없이 길다.
  + 거의 같은 두 개의 쿼리를 두 번이나 실행하고 있다. (성능)
- UNION 쿼리는 items에 TABLE ACCESS FULL로 2번 접근하고 있으므로, 읽어들이는 비용도 테이블의 크기에 따라 선형으로 증가하게 된다. 
- 물론 데이터 캐시에 테이블의 데이터가 있으면 어느 정도 완화되겠지만, 테이블의 크기가 커지면 캐시 히트율이 낮아지므로 그러한 것도 기대하기 힘들어진다. 
- UNION은 굉장히 편리한 도구지만 정확한 판단 없이 SELECT 구문 전체를 여러 번 사용해서 코드를 길게 만드는 것은 쓸데없는 테이블 접근을 발생시키며 SQL의 성능을 나쁘게 만든다. 또한 물리 자원도 쓸데없이 소비하게 된다. 
### 2. WHERE 구에서 조건 분기를 하는 사람은 초보자
- SELECT 구만으로 조건 분기를 하면 다음과 같이 최적화할 수 있다. 
```sql
SELECT item_name, year, 
    CASE WHEN year <= 2001 THEN price_tax_ex
         WHEN year >= 20002 THEN price_tax_in 
         END AS price 
FROM items
```
- UNION을 사용한 쿼리와 같은 결과를 출력하지만 성능적으로 훨씬 좋은 쿼리이다.(테이블이 크기가 커질수록 명확하게 드러남)
### 3. SELECT 구를 사용한 조건 분기의 실행 계획
- 실행 계획을 살펴보면, item 테이블에 대한 접근이 1회로 줄고, 가독성이 좋아졌다 
- UNION을 사용한 분기는 SELECT '구문'을 기본 단위로 분기하고 있다. 구문을 기본 단위로 사용하고 있다는 점에서, 아직 절차 지향형의 발상을 벗어나지 못했다. 반면 CASE 식을 사용한 분기는 문자 그대로 '식'을 바탕으로 하는 사고이다.
- `'구문'에서 '식'으로 사고를 변경`하는 것이 SQL을 마스터 하는 열쇠 중 하나이다. 

## 9강 집계와 조건 분기
- 집계를 수행하는 쿼리를 작성할 때, 쓸데없이 길어지는 경우를 자주 볼 수 있다. 
- 예제 
![인구테이블](./images/9강/인구테이블.png)
- 원하는 결과
![원하는결과](./images/9강/원하는결과.png)
### 1. 집계 대상으로 조건 분기 
- UNION을 사용한 방법
  + UNION을 이용해 절차 지향적으로 풀기
  ```sql
  SELECT prefecture, 
         SUM(pop_men) AS pop_men,
         SUM(pop_wom) AS pop_wom,
  FROM (
            SELECT prefecture, pop AS pop_men, NULL as pop_wom
            FROM population
            WHERE sex = '1'
            UNION
            SELECT prefecture, NULL AS pop_men, pop as pop_wom
            FROM population
            WHERE sex = '2'
       ) TMP
  GROUP BY prefecture;     
  ```
- UNION의 실행 계획
  + FULL SCAN 2회 발생
  + 만약, sex 필드에 인덱스가 존재하면 조건분기 보다 UNION 식이 더 빠르게 작동할 수 있다.
- 집계의 조건 분기도 CASE 식을 사용
  + 원래 SQL은 이러한 결과 포맷팅을 목적으로 만들어진 언어가 아니지만 실무에서 자주 사용되는 기술이다. 
  + SELECT 구문을 사용한 조건 분기의 경우 쿼리가 간단해진다. 
  ```sql
  SELECT prefecture,
         SUM(CASE WHEN sex = '1' THEN pop ELSE 0 END) AS pop_men,
         SUM(CASE WHEN sex = '2' THEN pop ELSE 0 END) AS pop_wom
  FROM population
  GROUP BY prefecture       
  ```  
- CASE 식의 실행 계획 
  + FULL SCAN 1회 발생
  + UNION을 사용한 경우에 비해 I/O 비용이 절반으로 감소
  + `CASE식으로 조건 분기를 잘 사용하면 UNION을 사용하지 않을 수 있다. 또한, 성능적인 측면에서도 좋다`




