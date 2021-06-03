/*
* 26. 로(raw) 타입은 사용하지 말아라.
* 클래스와 인터페이스 선언에 타입 매개변수가 쓰이면, 이를 제네릭 클래스 혹은 제네릭 인터페이스라 한다.
* 각각의 제네릭 타입은 일련의 매개변수화 타입을 정의한다.*/

import java.util.ArrayList;
import java.util.Collection;

public class Item26 {
    // strings에는 String 인스턴스만 들어가야 한다는 것을 컴파일러가 인지한다.
    private final Collection<String> strings = new ArrayList<>();
}

