# 자바로 배우는 리팩토링 입문

\[샘플코드]: https://github.com/gilbutITbook/006921

- [0장 리팩토링이란](#0장-리팩토링이란)
- [1장 매직 넘버를 기호 상수로 치환](#1장-매직-넘버를-기호-상수로-치환)
- [2장 제어플래그 삭제](#2장-제어플래그-삭제)
- [3장 어서션 도입](#3장-어서션-도입)
- [4장 널 객체 도입](#4장-널-객체-도입)
- [5장 메서드 추출](#5장-메서드-추출)
- [6장 클래스 추출](#6장-클래스-추출)
- [7장 분류 코드를 클래스로 치환](#7장-분류-코드를-클래스로-치환)
- [8장 분류 코드를 하위 클래스로 치환](#8장-분류-코드를-하위-클래스로-치환)
- [9장 분류 코드를 상태/전략 패턴으로 치환](#9장-분류-코드를-상태전략-패턴으로-치환)

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
- 매직 넘버를 `상수`나 `enum`으로 바꾸자
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
: 제어 플래그 때문에 코드가 읽기 어려운 경우
- 문제
  + 제어 플래그를 지나치게 사용하면 처리 흐름을 파악하기 어려움
- 해법  
  + 제어 플래그(flag) 대신에 `break`, `continue`, `return` 등을 써서 처리 흐름을 제어하자
  + 제어 플래그를 사용해야 한다면, flag라는 변수명 대신 변수명을 변경하여 뭘 의미하는지 나타내자 
    + `initialized`, `debug`, `error`, `done`, `interrupted`, `recurse`
- 리팩토링 전
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

## 3장 어서션 도입
: '이렇게 될 것이다'라는 주석이 있는 경우
- 문제 
  + 개발을 하다보면 '여기서 변수 value는 참일 것이다' 또는 '여기서 value > 0'이어야 한다 라는 경우가 생기는데, 주석 대신 어서션이라는 기법으로 프로그래머의 의도를 확실히 밝히면서도 실행 시 조건이 반드시 성립함을 보장하자.
- 결과
  + 해당 부분에서 성립해야 할 조건이 명확해지고 소스 코드가 읽기 좋아짐
  + 버그를 빨리 발견 가능함
  + 어서션을 활성화하면 어서션이 성립하는지 자동으로 확인 가능함
  + 어서션을 비활성화하면 어서션이 무시되어 성능이 개선됨 
  + 하지만 어서션을 지나치게 쓰면 읽기 어려워짐  
- `assert value > 0`로 표명한다. 만약 value > 0이 아니라면 java.lang.Error의 하위 클래스인 `java.lang.AssertionError` 예외 발생 
  + 에러 처리 코드는 작성하지 않는다. 



## 4장 널 객체 도입 
:null 확인이 너무 많은 경우
- 문제
  + `if(email != null)` 확인이 너무 많으면 `널 객체 도입`하자
- 해법  
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
- 리팩토링 전
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

## 5장 메서드 추출
: 코드가 너무 길어서 읽기 어려운 경우
- 문제
  + 한 메서드 안에 이런저런 세세한 처리가 많으면 메서드 하나가 너무 긺
- 해법   
  + 기존 메서드에서 묶을 수 있는 코드를 추출해 새로운 메서드를 작성
- 방법 
  + 새로운 메서드에 적절한 이름 붙이기
    + 메서드 이름은 `동사+명사` 순서로 짓는게 보통이다
    + 메서드 이름은 `무엇을 하는가(what to do)`를 알 수 있게 짓는 것이 중요     
- 리팩토링 전
```java
public void print(int times, String content)
{
    // 맨위 출력
    for(int i = 0 ; i < content.length(), i ++)
    {
        System.out.print("-");
    }

    // 내용 출력
    for(int i = 0 ; i < times, i ++)
    {
        System.out.println("content");
    }

    // 맨아래 출력
    for(int i = 0 ; i < content.length(), i ++)
    {
        System.out.print("-");
    }
}
```
- 리팩토링 후
```java
public void print(int times, String content)
{
   printBorder(content);
   printContent(times, content);
   printBorder(content);
}

private void printBorder(String content)
{
    for(int i = 0 ; i < content.length(), i ++)
    {
        System.out.print("-");
    }
}
private void prinContentBorder(int times, String content)
{
    for(int i = 0 ; i < times, i ++)
    {
        System.out.println("content");
    }
}
```

## 6장 클래스 추출
: 클래스의 책임이 너무 많은 경우
- 문제
  + 한 클래스가 너무 많은 책임을 지고 있을 경우
- 해법
  + 묶을 수 있는 필드와 메서드를 찾아 새로운 클래스로 추출
- 방법
  1. 새로운 클래스 작성 `class Author`
  2. 필드 이동 `authorName` -> `name`
  3. 메서드 이동      
  4. 추출한 클래스 검토 
- 한 걸음 더 나아가기
  + 양방향 링크는 피한다
    - `Book` 클래스와 `Author` 클래스를 서로 링크하지 않고, 단방향을 유지한다. 
  + 역 리팩토링
    - 클래스 추출을 지나치게 하지 않는다.    
- 리팩토링 전 
```java
public class Book
{
    private String title;
    private String isbn;

    private String authorName;
    private String authorEmail;

    public String getAuthorName()
    {
        return authorName;
    }
}
```
- 리팩토링 후 
```java
public class Book
{
    private String title;
    private String isbn;

    private Author author;

    public String getAuthorName()
    {
        return author.getName();
    }
}
class Author
{
    private String name;
    private String email;

    public String getName()
    {
        return name;
    }
}
```

## 7장 분류 코드를 클래스로 치환
: int로 객체를 구분하는 경우 
- 문제
  + 분류 종류를 int 같은 기본 타입으로 분류하는 경우, `이상한 값`이 되거나 `다른 분류 코드와 혼동`될 수 있다.  
- 해법
  + 분류 종류를 나타내는 새로운 클래스를 작성. 기본형 대신 새로운 껍데기를 씌우는 방법. `int -> ItemType class` 
- 한 걸음 더 나아가기
  + [class 대신 `enum`을 사용](./7_ReplaceTypeCodeWithClass/advanced/ItemTypeEnum.java)   
- 리팩토링 전 
```java
public class Item
{
    public satic final int TYPECODE_BOOK = 0;
    public satic final int TYPECODE_DVD = 1;
    public satic final int TYPECODE_SOFTWARE = 2;

    private final int typecode;
}
```
- [리팩토링 후](./7_ReplaceTypeCodeWithClass/refactoring/ItemType.java) 
```java    
public class ItemType
{
    public static final ItemType BOOK = new ItemType(0);
    public static final ItemType DVD = new ItemType(1);
    public static final ItemType SOFTWARE = new ItemType(2);
    ...
}
public class Item
{
    private final ItemType itemType;
}
```

## 8장 분류 코드를 하위 클래스로 치환
: 분류 코드마다 `동작`이 다른 경우(1)
- 상황
  + 분류 코드마다 객체가 다른 동작을 함 
- 문제
  + switch 문을 써서 동작을 구분함. 객체 지향 프로그래밍이라면 switch 문은 악취가 난다고 판단한다. 
- 해법
  + 분류 코드를 하위 클래스로 치환해서 다형적 메서드를 작성
- 한 걸음 더 나아가기
  + [createShape() 생성자는 여전히 악취를 풍기고 있다. 여기서 더 리팩토링을 진행할 수도 있지만, 필요 이상으로 코드가 복잡해 질수 있으므로 균형잡힌 리팩토링이 필요하다.](./8_ReplaceTypeCodeWithSubclasses/advanced/AbstractShape.java) 
  + `프로그램 규모가 점점 커져서 전체를 파악하기 어려울 때, 기능 추가 예정이 있을 경우 리팩토링을 하는 게 좋다.` 
  + switch 문과 instanceof 연산자가 풍기는 악취 
```java
if (obj instanceof ShapeLine)
{
    ..
}
else if (obj instanceof Shapeectangle)
{
    ...
}
else if (obj instanceof ShapeecOval)
{
    ...
}
```    
- [리팩토링 전](./8_ReplaceTypeCodeWithSubclasses/before/Shape.java)  
```java
public class Shape 
{
    private final int typecode;
    ...
    public void draw()
    {
        switch (typecode)
        {
            case TYPECODE_LINE:
                ...
                break;

            case TYPECODE_RECTANGLE:
                ...
                break;
            
            case TYPECODE_OVAL:
                ...
                break;

            default: ;
        }
    }
}    
```
- [리팩토링 후](./8_ReplaceTypeCodeWithSubclasses/refactoring/Shape.java) 
```java
public abstract class Shape
{
    public abstract void draw();
}
public class ShapeLine extends Shape
{
    @Override public void draw() { ... }
}
public class ShapeRectangle extends Shape
{
    @Override public void draw() { ... }
}
public class ShapeOval extends Shape
{
    @Override public void draw() { ... }
}
```

## 9장 분류 코드를 상태/전략 패턴으로 치환
: 분류 코드마다 `동작`이 다른 경우(2)
- 8장과 9장의 차이
  + 8장에서 배운 분류 코드를 하위 클래스로 치환은 프로그램 실행 중에 객체 분류 코드가 변한다면 사용할 수 없다. 그럴 때는 분류 코드를 상태/전략 패턴으로 치환 방법을 사용해야 한다. 
  + 코드 중간에 ShapeLine이 ShapeRectangle로 바뀌지 않을 때는 8장 방법을 사용하지만, 바뀐다면 9장 방법을 사용해야 함.
  + 8장은 생성자에서 객체를 만들기 때문에 실행 중에 도형 종류가 변하지 않는 리팩토링이지만, 9장은 인스턴스의 특정 메서드를 호출할 때마다 상태가 변경되는 상태/전략 패턴을 이용한 리팩토링이다.   
- 상황
  + 분류 코드마다 객체가 다른 동작을 함 
- 문제
  + `동작을 switch문으로 나누고 있지만 분류 코드가 동적으로 변하므로 분류 코드를 하위 클래스로 치환은 사용 불가`
- 해법 
  + 분류 코드를 나타내는 새로운 클래스를 작성해서 상태/전략 패턴을 사용함
- 한 걸음 더 나아가기 
  + 상태 패턴과 전략 패턴의 차이
    - 목적이 다를 뿐 구조적 차이는 없음
    - `상태(state) 패턴`: 동일한 동작을 객체의 상태마다 다르게 수행해야 할 경우 하위 클래스 메서드로 작성하는 방법 ex)`Logger.start()`
    - `전략(strategy) 패턴`: 어떻게 객체가 일을 할지 정한다. 같은 문제를 해결하는 알고리즘 방식이 여러 가지가 있다면, 요구에 따른 알고리즘을 선택하여 사용할 수 있도록 하는 패턴이다. 즉, 필요에 따라 클래스를 택하여 갈아 끼우는 방식   
- [리팩토링 전](./9_ReplaceTypeCodeWithStateStrategy/before/Logger.java) 
```java
public class Logger 
{
    public static final int STATE_STOPPED = 0;
	public static final int STATE_LOGGING = 1;
	
	private int state;
	
	public Logger()
	{
		this.state = STATE_STOPPED;
	}
	
	public void start()
	{
		switch (state)
		{
			case STATE_STOPPED:
				System.out.println("** START LOGGING **");
				state = STATE_LOGGING;
				break;
				
			case STATE_LOGGING:
				break;	
			
			default:
				System.out.println("Inbalid state: " + state);
		}
	}
	...
}
```
- [리팩토링 후](./9_ReplaceTypeCodeWithStateStrategy/refactoring/Logger.java)
```java
public class Logger 
{
	private State state;
	
	public Logger()
	{
		setState(State.STOPPED);
    }

	public void start()
	{
		state.start();
		setState(State.LOGGING);
	}
    ...
}    
```




