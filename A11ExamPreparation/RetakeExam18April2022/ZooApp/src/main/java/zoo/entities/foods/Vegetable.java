package zoo.entities.foods;

public class Vegetable extends BaseFood {
    public static final int DEFAULT_CALORIES = 50;
    public static final double DEFAULT_PRICE = 5;

    public Vegetable() {
        super(DEFAULT_CALORIES, DEFAULT_PRICE);
    }
}
