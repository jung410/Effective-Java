/*
* 다 쓴 객체 참조를 해제하라
* - GC가 알아서 다 해줄 것 처럼 보이지만 아니다.*/

import java.util.Arrays;
import java.util.EmptyStackException;

public class Item07 {

}

/*
* 아래와 같은 스택이 메모리 누수에 취약한 이유는
* 스택에서 관리하는 배열(elements)의 원소들이 실제 사용되는지, 사용되지 않는지는
* 개발자밖에 모르기 때문이다.
* 비활성 영역의 원소가 사용되지 않는 다는 것을 우리는 알고 있지만 GC는 알 방법이 없다.
* : 일반적으로 자기 메모리를 직접 관리하는 클래스라면 항시 메모리 누수에 주의해야 한다.*/
class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 원래대로 라면 아래에서 꺼낸 객체를 GC가 회수하지 않는다.
        // : 이 스택이 그 객체들의 다 쓴 참조(obsolete reference)를 여전히 가지고 있기 때문.
//        return elements[--size];

        // 해당 참조를 다 썼을 경우 null 처리를 해준다.
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    /*
     * 원소를 위한 공간을 적오도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.*/
    private void ensureCapacity(){
        if(elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

/*
* 캐시 또한 메모리 누수의 주범
* 객체 참조를 개시에 넣고 나서 잊을 수 있다.
* : WeakHashMap을 사용하면 되지만 이런 경우에만 유용하다.*/

/*
* 리스너 or 콜백이라 부르는 것 역시 메모리 누수의 주범
* 클라이언트가 콜백을 등록만 하고 명확히 해지하지 않는다면, 콜백은 계속 쌓임
* 이 역시 콜백을 WeakHashMap으로 저장하면 GC가 처리할 수 있게된다.*/