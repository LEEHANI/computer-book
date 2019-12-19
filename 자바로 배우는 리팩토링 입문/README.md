# 자바로 배우는 리팩토링 입문

\[샘플코드]: https://github.com/gilbutITbook/006921

- [0장 리팩토링이란](#0장-리팩토링이란)
- [1장 매직 넘버를 기호 상수로 치환](#1장-매직-넘버를-기호-상수로-치환)
- [2장 제어플래그 삭제](#2장-제어플래그-삭제)
- [4장 널 객체 도입](#4장-널-객체-도입)

## 0장 리팩토링이란 
- `외부에서 보는 프로그램 동작은 바꾸지 않고 프로그램의 내부 구조를 개선하는 것`
- 리팩토링을 하기 전과 후에 반드시 테스트를 진행해야 한다. `테스트` -> `리팩토링` -> `다시 테스트`
- 리팩토링 목적
  + 버그를 발견하기 쉽게 만든다
  + 기능을 추가하기 쉽게 만든다
  + 리뷰하기 쉽게 만든다 
- 리팩토링의 한계
  + 프로그램이 아직 동작하지 않을 때
  + 시간이 너무 촉박할 때


## 1장 매직 넘버를 기호 상수로 치환
- 매직 넘버를 상수나 enum으로 바꾸자
  + 매직 넘버만 보고는 숫자의 의미를 알기 어렵다
  + 소스 코드 이곳 저곳에 있으므로 매직 넘버는 수정하기 어려움
- `200` -> `MAX_LENGTH` 
- for문에서는 기호 상수 대신 length를 쓰자
  + for(int i = 0 ; i < `arr.length` ; i ++ )
```java
if (work == 1)
{
    System.out.println("결제합니다")
}
else if (work == 2)
{
    System.out.println("상품을 준비중입니다")
}
else if (work == 3)
{
    System.out.println("배송을 시작합니다")
}
```
- 리팩토링
```java
public static final BUY = 1;
public static final PREPARE_PRODUCT = 1;
public static final DELIVERY = 1;
if (work == BUY)
{
    System.out.println("결제합니다")
}
else if (work == PREPARE_PRODUCT)
{
    System.out.println("상품을 준비중입니다")
}
else if (work == DELIVERY)
{
    System.out.println("배송을 시작합니다")
}
```


## 2장 제어플래그 삭제
- 제어 플래그(flag) 대신에 `break`, `continue`, `return` 등을 써서 처리 흐름을 제어하자
  + 제어 플래그를 지나치게 사용하면 처리 흐름을 파악하기 어려움
- flag라는 변수명 대신 변수명을 변경하여 뭘 의미하는지 나타내자 
  + `initialized`, `debug`, `error`, `done`, `interrupted`, `recurse`
- 리팩토링 시작
```java
boolean flag = true

if(flag)
{
    result = false;
}
else
{
    result = true;
}

return result;
```
- 리팩토링 후 
```java
if(done)
{
   return false;
}
else
{
    return true;
}

```


## 4장 널 객체 도입 
:null 확인이 너무 많은 경우
- `if(email != null)` 확인이 너무 많으면 `널 객체 도입`하자
  + [null인지 확인하는 코드가 소스코드 곳곳에 존재](./4_Introduce_Null_Object/before/Person.java) -> [NullLabel 클래스 도입](./4_Introduce_Null_Object/refactoring/NullLabel.java)
- null 확인을 모두 제거 하기위해 리팩토링을 하는게 아니다. null 확인이 너무 많아서 빠뜨리거나 실수할 것 같을 때 써야한다. 
- 방법
  + 널 클래스 작성 `NullLabel.java`
    - isNull 메서드 작성
  + null 치환 `new NullLabel()`
  + 널 객체 클래스를 재정의해서 조건 판단 삭제하기
```java
if (obj.isNull())
{
    <null 동작>
}
else
{
    obj.doSomething();
}
```
  + if문 조건 판단 삭제해서 `obj.doSomething()`만 남기기
- 리팩토링 시작
```java
public Person(Label name)
{
    this(name, null);
}

...

public void display()
{
    if(name != null)
    {
        name.display();
    }
    
    if(email != null)
    {
        email.display();
    }
}
```
- 리팩토링 후
```java
public Person(Label name)
{
    this(name, new NullLabel());
}

...

public void display()
{
    name.display();
    email.display();
}
```