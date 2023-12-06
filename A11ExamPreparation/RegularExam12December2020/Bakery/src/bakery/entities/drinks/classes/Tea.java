package bakery.entities.drinks.classes;

public class Tea extends BaseDrink {
    public static final double DEFAULT_PRICE = 2.5;

    public Tea(String name, int portion, String brand) {
        super(name, portion, DEFAULT_PRICE, brand);
    }
}
