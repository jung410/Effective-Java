/*
* 18. 상속보다는 컴포지션을 사용하라.
* 상속은 코드를 재사용하는 강력한 수단이지만, 항상 최선은 아니다.
* 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다.*/

import java.util.Collection;
import java.util.HashSet;

public class Item18 {
}

/*
* 이런 방식의 class 상속은 상위 클래스가 변하거나 상위 클래스의 메서드를
* 재정의(override)했을 경우 문제가 생길 여지가 다분하다.*/
class InstrumentedHashSet<E> extends HashSet<E> {
    // 추가된 원소의 수
    private int addCount = 0;

    public InstrumentedHashSet() {

    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

/*
* 그래서 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 하도록 하는
* 컴포지션(composition)을 활용한다.*/