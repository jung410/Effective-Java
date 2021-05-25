/*
* 3장. 모든 객체의 공통 메서드*/

/*
* equals는 일반 규약을 지켜 재정의하라
* equlas 메서드는 재정의하기 쉬워 보이지만 곳곳에 함정이 도사리고 있어서 자칫하면 끔찍한 결과를 초래한다.
* 가장 좋은 방법은 재정의 하지 않는것.
* 아래 상황 중 하나에 해당한다면 재정의하지 않는 것이 최선!
* 1. 각 인스턴스가 본질적으로 고유하다.
* 2. 인스턴스의 '논리적 동치성(logical equality)'을 검사할 일이 없다.
* 3. 상위 클래스에서 재정의한 equals가 하위 클래스에서도 딱 들어맞는다.
* 4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없다.*/

import java.awt.*;
import java.util.Objects;

public class Item10 {
}

class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // 이렇게 쓰면 안되고
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof CaseInsensitiveString)
//            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
//        if (obj instanceof String) // 이 코드는 한 방향으로만 작동한다. -> 대칭성을 위배한다.
//            return s.equalsIgnoreCase((String) obj);
//        return false;
//    }

    // 이렇게 써야된다.
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CaseInsensitiveString && ((CaseInsensitiveString) obj).s.equalsIgnoreCase(s);
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }
}

class ColorPoint extends Point{
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
}

// 전형적인 equals 메서드의 예
class PhoneNumber{
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(short areaCode, short prefix, short lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg){
        if(val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(!(obj instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber) obj;
        return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;

    }
}