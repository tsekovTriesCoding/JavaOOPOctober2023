package A3Inheritance.Lab.P03RandomArrayList;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<T> extends ArrayList<T> {
    private final static Random random = ThreadLocalRandom.current();

    public T getRandomElement() {
        int randomIndex = random.nextInt(super.size());

        return super.get(randomIndex);
    }
}
