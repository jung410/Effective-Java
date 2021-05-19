/*
* finalizer와 cleaner 사용을 피하라
* - 자바는 두 가지 객체 소멸자를 제공한다.
* 1. finailzer : 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
* - 사용하지 않는것을 권장한다. (java9부터 사라짐)
* 2. cleaner : finalizer보다 덜 위험하지만, 여전히 예측할 수 없고, 느리고, 일반적으로 불필요하다.*/

/*
* 잘 이해가 되진 않지만.. 꼭 필요할 때가 아니라면 cleaner를 사용하지 말아라.*/

import java.lang.ref.Cleaner;

// cleaner를 안전망으로 활용하는 AutoCloseable 클래스
public class Item08 implements AutoCloseable{

    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원. 절대 Item08을 참조해서는 안된다.
    private static class State implements Runnable {
        int numJunkPiles; // Item08 안의 쓰레기 수

        State(int numJunkPiles){
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("방청소");
            numJunkPiles = 0;
        }
    }

    // 방의 상태. cleanable과 공유한다.
    private final State state;

    private final Cleaner.Cleanable cleanable;

    public Item08(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
