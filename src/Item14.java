/*
* 14. Comparable을 구현할지 고려하라.
* compare의 규약은 equals의 규약과 거의 같다.
* 순서를 고려해야 하는 값 클래스를 작성한다면 꼭 Comparable interface를 구현하여,
* 그 인스턴스들을 쉽게 정렬하고, 검색하고, 비교 기능을 제공하는 컬렉션과 어우러지도록 해야한다.
* compareTo method에서 필드의 값을 비교할 때 < 나 > 연산자는 사용하지 말아라.
* 그 대신 박싱된 기본 타입 클래스가 제공하는 정적 compare method나 Comparator 인터페이스가 제공하는
* 비교자 생성 메서드를 사용하자.*/

import java.util.Comparator;

public class Item14 {

    // 정적 compare method를 활용한 비교자 -> 이게 많이 보던 방식이다.
    static Comparator<Object> hashCodeOrder = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    };

    // 비교자 생성 메서드를 활용한 비교자
    static Comparator<Object> hashCodeOrder2 = Comparator.comparingInt(o -> o.hashCode());
}
