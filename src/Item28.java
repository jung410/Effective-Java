import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item28 {
    public static void main(String[] args) {
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "다른 타입은 넣을 수 없다.";

//        List<Object> ol = new ArrayList<Long>();
//        ol.add("다른 타입은 넣을 수 없다.");

        // 배열은 런타임에 알 수 있지만 리스트는 컴파일에 알 수 있다.

        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);

//        String[] stringArray = new String[1];
//        stringArray[0] = "aaaa";
        Chooser chooser = new Chooser(stringList);
        Chooser chooser2 = new Chooser(integerList);
        String str = (String)chooser.choose();
        int a = (Integer)chooser.choose();

    }
}

class Chooser {
    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        choiceArray = choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}

class Chooser2<T> {
    private final T[] choiceArray;

    public Chooser2(Collection<T> choices) {
        choiceArray = (T[]) choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}

class Chooser3<T> {
    private final List<T> choiceArray;

    public Chooser3(Collection<T> choices) {
        choiceArray = new ArrayList<>(choices);
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray.get(rnd.nextInt(choiceArray.size()));
    }
}