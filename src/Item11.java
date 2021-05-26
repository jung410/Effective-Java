import java.util.HashMap;
import java.util.Map;

/*
* 11. equals를 재정의하려거든 hashCode도 재정의해라.
* equals를 재정의한 클래스 모두에서 hashCode를 재정의하지 않으면 hashCode 일반규약을 어기게 되어 해당 클래스의
* 인스턴스를 HashMap이나 HashSet같은 컬렉션의 원소로 사용할 때 문제를 일으킬 수 있다.*/
public class Item11 {
    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber((short)707, (short)867, (short)5309), "제니");

        System.out.println(m.get(new PhoneNumber((short)707, (short)867, (short)5309)));
    }
}
