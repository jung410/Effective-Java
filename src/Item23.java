/*
* 23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라.
* 태그 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.
* 태그 달린 클래스는 클래스 계층구조를 어설프게 흉내낸 아류일 뿐이다.*/

public class Item23 {
}

// 태그 달린 클래스 - 클래스 계층구조보다 훨씬 나쁘다.
class Figure{
    enum Shape {RECTANGLE, CIRCLE};

    final Shape shape;

    // 모양이 사각형(RECTANGLE)일 때만 쓰인다.
    double length;
    double width;

    // 모양이 원(CIRCLE)일 때만 쓰인다.
    double radius;

    // '원'용 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // '사각형' 용 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}

// 위의 잘못된 클래스를 바꿔보자.
abstract class Figure2 {
    abstract double area();
}

class Circle extends Figure2 {
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }
    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends Figure2{
    final double length;
    final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}