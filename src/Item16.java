/*
* 16. public class 에서는 public field가 아닌 접근자 메서드를 사용하라*/

public class Item16 {
}

// 잘못된 class
class Point2 {
    public double x;
    public double y;
}

// 접근자와 변경자(mutator) 메서드를 활용해 데이터를 캡슐화 해야 객체지향 적인 것.
class Point3 {
    private double x;
    private double y;

    public Point3(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

// public class라면 위 방식이 맞지만
// package-private or private 중첩 클래스라면 데이터 필드를 노출한다 해도 문제가 없다.
// 그 클래스가 표현하려는 추상 개념만 올바르게 표현해 주면 된다.