/*
* 불필요한 객체 생성을 피하라*/

import java.util.Scanner;
import java.util.regex.Pattern;

public class Item06 {
    public static void main(String[] args) {
//        String s = new String("abc"); // 완전 구림!
//
//        String s1 = "abc"; // 재사용될 수 있는 객체는 재사용 해라.
//
//        // 둘 중 아래의 방법이 훨씬 빠르고 코드의 명확성도 높다.
//        boolean b = isRomanNumeral("abc");
//        RomanNumerals.isRomanNumeral("abc");

        System.out.println(sum());


    }


    // 박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의해라.
    private static long sum() {
//        Long sum = 0L;
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++){
            sum += i;
        }
        return sum;
    }

    /*이 방식의 문제는 String.matches 메서드를 사용한다는 것.
    * 성능이 중요한 상황에서 반복하여 사용하기엔 별로.*/
    static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*......blahblah");
    }

}

class RomanNumerals {
    /*
    * String.matches 내부에서 만드는 Pattern 인스턴스를
    * 아래와 같이 미리 캐싱해놓아 재사용할 수 있도록 만드는 방법*/
    private static final Pattern ROMAN = Pattern.compile(
            "^(=.)M*.....blahblah"
    );

    static boolean isRomanNumeral(String s){
        return ROMAN.matcher(s).matches();
    }
}
