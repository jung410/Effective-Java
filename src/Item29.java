import java.util.Arrays;
import java.util.EmptyStackException;

public class Item29 {
    public static void main(String[] args) {

    }
}

@SuppressWarnings("unchecked")
class Stack2<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack2() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 원래대로 라면 아래에서 꺼낸 객체를 GC가 회수하지 않는다.
        // : 이 스택이 그 객체들의 다 쓴 참조(obsolete reference)를 여전히 가지고 있기 때문.
//        return elements[--size];

        // 해당 참조를 다 썼을 경우 null 처리를 해준다.
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    /*
     * 원소를 위한 공간을 적오도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.*/
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
