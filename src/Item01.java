/*
 * 정적 팩토리 메서드
 * - 우리가 흔히 사용하는 기본 생성자 대신 객체를 리턴하는 명확한 이름을 가진 정적 메서드를 제공함으로써
 *   메모리를 많이 차지하는 new 연산자를 사용할 필요가 없고, 하위 자료형 객체를 반환할 수 있는 장점을 가진다.
 * */
public class Item01 {
    public static void main(String[] args) {
        Monitor samsungMonitor = new Monitor("삼성", "IPS", 32, "black", 4, "16:9", 340000);
        Monitor lgMonitor = new Monitor("LG", "IPS", 32, "black", 4, "16:9", 340000);

        Monitor samsungMonitor2 = Monitor.makeSamsungMonitor();
        Monitor lgMonitor2 = Monitor.makeLgMonitor();

        System.out.println("hello world");

        System.out.println(valueOf(true));
        System.out.println(valueOf(false));
        System.out.println(Boolean.valueOf(true));
    }

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}