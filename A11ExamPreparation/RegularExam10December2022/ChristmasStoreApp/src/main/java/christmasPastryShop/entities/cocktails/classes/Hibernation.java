package christmasPastryShop.entities.cocktails.classes;

public class Hibernation extends BaseCocktail {
    public static final double DEFAULT_PRICE = 4.5;

    public Hibernation(String name, int size, String brand) {
        super(name, size, DEFAULT_PRICE, brand);
    }
}
