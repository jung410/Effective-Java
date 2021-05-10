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

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

class main {
    public static void main(String[] args) {
        Item02 cocaCola = new Item02.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola.toString());


        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        System.out.println(pizza.toppings);
        Calzone calzone = new Calzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();
        System.out.println(calzone.toppings);

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

/*
* Builder pattern은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.*/

// 추상클래스를 하나 만들고(피자)
abstract class Pizza{
    // enum타입의 토핑을 가지고 있다.
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}
    // 토핑Set
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        // 빈 toppings를 만든다.
        // EnumSet.nonOf : 빈 enum set을 만드는 메서드
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의하여 "this"를 반환하도록 해야한다.
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}

// Pizza를 상속받아 NyPizza class를 만든다.
class NyPizza extends Pizza{
    public enum Size {SMALL, MEDIUM, LARGE}
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{
        public final Size size;

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}

class Calzone extends Pizza{
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder){
        super(builder);
        sauceInside = builder.sauceInside;
    }
}