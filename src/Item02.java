/*
 * 생성자에 매개변수가 많다면 빌더를 고려하라
 * - 정적 팩토리와 생성자는 선택적 매개변수가 많을때 적절히 대응하기 어렵다는 제약이 존재한다.
 * - 만약 class 내의 field가 많을경우 모든 경우에 대한 생성자를 만들어줘야 하는 문제가 생긴다.
 *
 * 그 대안으로
 * 첫번째, JavaBeans pattern 이 있다.
 *  : setter 메서드를 이용하는 방법
 *  : but, 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까지 일관성이 무너진 상태이다.
 * 두번째, Builder pattern
 *  : 필요한 객체를 직접 만드는 대신, 필수 매개변수만으로 생성자를 호출해 빌더 객체를 얻을 수 있다.*/

class main {
    public static void main(String[] args) {
        Item02 cocaCola = new Item02.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola.toString());
    }
}

public class Item02 {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Item02 build() {
            return new Item02(this);
        }
    }

    private Item02(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "servingSize = " + this.servingSize + "\n"
                + "servings = " + this.servings + "\n"
                + "calories = " + this.calories + "\n"
                + "fat = " + this.fat + "\n"
                + "sodium = " + this.sodium + "\n"
                + "carbohydrate = " + this.carbohydrate + "\n";
    }
}