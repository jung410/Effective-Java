import java.math.BigInteger;

/*
 * 17. 변경 가능성을 최소화하라.
 * 불변클래스 : 그 인스턴스의 내부 값을 수정할 수 없는 클래스
 * 1. 객체의 상태를 변경하는 메서드를 제공하지 않는다.
 * 2. 클래스를 확장할 수 없도록 한다.
 * 3. 모든 필드를 final로 선언한다.
 * 4. 모든 필드를 private로 선언한다.
 * 5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.*/
public class Item17 {
    public static void main(String[] args) {
        Complex complex = new Complex(1, 0);
        System.out.println(complex.realPart());
        System.out.println(complex.plus(new Complex(2, 3)));
    }

}

final class Complex {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if(!(o instanceof Complex)) {
            return false;
        }
        Complex c = (Complex) o;
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}