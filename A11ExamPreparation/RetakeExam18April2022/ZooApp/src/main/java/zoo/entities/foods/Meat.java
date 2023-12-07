package zoo.entities.foods;

public class Meat extends BaseFood {
    public static final int DEFAULT_CALORIES = 70;
    public static final double DEFAULT_PRICE = 10;

    public Meat() {
        super(DEFAULT_CALORIES, DEFAULT_PRICE);
    }
}
