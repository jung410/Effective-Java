/*
 * 20. 추상클래스보다는 인터페이스를 우선하라.
 * 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다.
 * 인터페이스는 믹스인(mixin) 정의에 안성맞춤
 * 믹스인 : 클래스가 구현할 수 있는 타입
 * 인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있다.*/

/*
 * 래퍼 클래스 관용구와 함께 사용하면 인터페이스는 기능을 향상시키는
 * 안전하고 강력한 수단이 된다.*/

import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Item20 implements SingerSongWriter {
    @Override
    public AudioClip sing(Song s) {
        System.out.println("sing~~~");
        return null;
    }

    @Override
    public Song compose(int chartPosition) {
        System.out.println("compose~~~~");
        return null;
    }

    // int배열을 받아 Interger 인스턴스의 리스트 형태로 보여주는 어댑터(Adapter)
    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        // 익명클래스
        return new AbstractList<>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            // 박싱과 언박싱 때문에 성능은 그리 좋지 않다.
            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    public static void main(String[] args) {
        int[] intArray = {4, 2, 3};

        List<Integer> tempList = intArrayAsList(intArray);

        for (Integer i : tempList
        ) {
            System.out.println(i);
        }

    }
}

class Song {

}

class AudioClip {

}

interface Singer {
    AudioClip sing(Song s);
}

interface SongWriter {
    Song compose(int chartPosition);
}

interface SingerSongWriter extends Singer, SongWriter {

}

// 골격 구현 클래스
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

    // 변경 가능한 에트리는 이 멧드를 반드시 재정의해야 한다.
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    // Map.Entry.equals의 일반규약을 구현한다.
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry<?, ?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());

    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}

class MapEntry<K, V> extends AbstractMapEntry<K, V>{

    @Override
    public K getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }
}
