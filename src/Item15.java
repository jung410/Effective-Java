/*
* 4장 클래스와 인터피에스*/

/*
* 15. 클래스와 멤버의 접근 권한을 최소하하라.
* - 잘 설계된 컴포넌트는 내부 데이터와 내부 구현 정보를 얼마나 잘 숨겼냐에서 나타난다.
* - 이를 우리는 캡슐화라고 하며, 이 캡슐화는 소프트웨어 설계의 근간이 되는 원리이다.*/

/*
* 1. 접근 제어 메커니즘
* - 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.
* - public일 필요가 없는 클래스의 접근 수준을 package-private 톱레벨 클래스로 좁히는것.
* - public class의 인스턴스 field는 되도록 public이 아니어야 한다.
* - 또한 클래스에서 public static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공해서는 안된다.*/

/*
* public class는 상슈용 public static final field외에는 어떠한 public field도 가져서는 안된다.
* public static final field가 참조하는 객체가 불변인지 확인하라.*/
public class Item15 {
}
