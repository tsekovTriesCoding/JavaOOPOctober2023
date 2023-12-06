package bakery.entities.drinks.classes;

public class Water extends BaseDrink {
    public static final double DEFAULT_PRICE = 1.5;

    public Water(String name, int portion, String brand) {
        super(name, portion, DEFAULT_PRICE, brand);
    }
}
