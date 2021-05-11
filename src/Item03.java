/*
* 싱글턴이란 인스턴스를 오직 하나만 생성할 수 있도록 하는 것.
* ex) 상태가 없는 개체나, 설계상 유일해야 하는 시스템 컴포넌트 등
* */
public class Item03 {
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();

        Elvis2 elvis2 = Elvis2.getInstance();
        elvis2.leaveTheBuilding();

        Elvis3 elvis3 = Elvis3.INSTANCE;
        elvis3.leaveTheBuilding();
    }
}

// 싱글턴을 만드는 첫번째 방법
class Elvis{
    public static final Elvis INSTANCE = new Elvis();

    // 생성자를 private로 숨기고
    // 인스턴스에 접근할 수 있는 유일한 수단으로 public static 멤버를 하나 만든다.(위)
    private Elvis() {

    }

    public void leaveTheBuilding() {}
}

// 싱글턴을 만드는 두번째 방법
class Elvis2{
    private static final Elvis2 INSTANCE = new Elvis2();
    private Elvis2() {

    }

    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {}

    // 싱글턴임을 보장해주는 메서드
    private Object readResolve() {
        // 진짜 Elvis2를 반환하고, 가짜 Elvis2는 GC에 맡긴다.
        return INSTANCE;
    }
}

// 열거 타입 방식의 싱글턴
// 대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법
// 하지만 만들려는 싱글턴으 Enum 외의 클래스를 상속해야 한다면 사용 불가.
enum Elvis3{
    INSTANCE;

    public void leaveTheBuilding() {}
}