/*
* 22. 인터페이스는 타입을 정의하는 용도로만 사용하라.
* 인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.
* 클래스가 어떤 인터페이스를 구현한다는 것은 자신의 인스턴스로 무엇을 할 수 있는지 클라이언트에 얘기해 주는 것.
* 인터페이스는 오직 이 용도로만 사용해야한다.
* - 상수 공개용 수단으로 사용하지 말자.*/

// 잘못된 예(상수 인터페이스 안티패턴 - 사용금지!!!)
interface PhysicalConstants{
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}

// 위와같이 하지말고 아래와 같이 '유틸리티 클래스'를 만들어 사용해라

class PhysicalConstants2{
    private PhysicalConstants2() {} // 인스턴스화 방지

    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    public static final double ELECTRON_MASS = 9.109_383_56e-31;
}

public class Item22 {
    public static void main(String[] args) {
        System.out.println(PhysicalConstants2.AVOGADROS_NUMBER);
    }
}

