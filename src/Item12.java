/*
* 12. toString을 항상 재정의해라.
* !! 모든 하위 클래스에서 이 메서드를 재정의해라 !!
* - 그 객체가 가진 주요 정보 모두를 반환하는게 좋다.*/
public class Item12 {
}

class PhoneNumber2{
    private final short areaCode, prefix, lineNum;

    public PhoneNumber2(short areaCode, short prefix, short lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg){
        if(val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(!(obj instanceof PhoneNumber2)) return false;
        PhoneNumber2 pn = (PhoneNumber2) obj;
        return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
    }

    // 포맷을 정확하게 만들 경우
    /**
     * 이 전화번호의 문자열 표현을 반환한다.
     * 이 문자열은 "XXX-YYY-ZZZZ" 형태의 12글자로 구성된다.
     * XXX는 지역코드, YYY는 프리픽스, ZZZZ는 가입자 번호다.
     * 각각의 대문자는 10진수 숫자 하나를 나타낸다.
     *
     * 전화번호의 각 부분으 ㅣ값이 너무 작아서 자릿수를 채울 수 없다면,
     * 앞에서부터 0으로 채워나간다. 예를들어 가입자 번호가 123이라면
     * 전화번호의 마지막 네 문자는 "0123"이 된다.*/
    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }
}