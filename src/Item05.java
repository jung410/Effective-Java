/*
* 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
* - 많은 클래스가 하나 이상의 자원에 의존한다.*/

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item05 {
    public static void main(String[] args) {
        int number = 1;
        /*
        * number : 변수이름
        * int : 데이터 타입
        * 1 : 값(data), 리터럴
        * = : 대입연산자
        * */
        int number1 = 1;
        // int라는 데이터타입을 가진 number1이라는 변수에 1을 대입한다.
        Writer out;
        String str1 = new String("abcd");

        // 100% 클래스는 객체화(인스턴스화)할 때 "new Class()"

        // 1 = new 1
        // PrintWriter라는 데이터타입을 가진 p1이라는 변수에 new PrintWriter를 넣는다.
        // new PrintWriter : 데이터 == 1
        // new : 새로운 => 연산자 + - x / new
        // PrintWriter : 클래스
        // new PrintWiter : 새로운 클래스(그 설계도로 만들어진 실체) => 객체(인스턴스)
        // p1이라는 변수이름을 가진 변수의 데이터타입은 PrintWriter다.


        String str = "abcd";
    }
}

// 정적 유틸리티를 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
class SpellChecker {
    private static final Lexicon dictionary = new Lexicon();

    private SpellChecker() {} // 객체 생성 방지

    public static boolean isValid(String word) {
        return true;
    }
    public static List<String> suggestions(String typo) {
        List<String> list = new ArrayList<>();
        return list;
    }
}

// 싱글턴을 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
class SpellChecker2 {
    private final Lexicon dictionary = new Lexicon();

    private SpellChecker2() {} // 객체 생성 방지
    public static SpellChecker2 INSTANCE = new SpellChecker2();

    public static boolean isValid(String word) {
        return true;
    }
    public static List<String> suggestions(String typo) {
        List<String> list = new ArrayList<>();
        return list;
    }
}

class Lexicon {

}

/*
 * 위 두 방식 모두 사전을 단 하나만 사용한다고 가정하기 때문에 좋지 않다.*/

// SpellChecker가 여러사전을 사용할 수 있도록 만들어보자.
// 인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식
// 이는 의존 객체 주입의 한 형태
class SpellChecker3{
    private final Lexicon dictionary;

    public SpellChecker3(Lexicon dictionary){
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public static boolean isValid(String word) {
        return true;
    }
    public static List<String> suggestions(String typo) {
        List<String> list = new ArrayList<>();
        return list;
    }
}