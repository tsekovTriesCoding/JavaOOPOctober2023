package christmasPastryShop.entities.cocktails.interfaces;

public class MulledWine extends BaseCocktail {
    public static final double DEFAULT_PRICE = 3.50;

    public MulledWine(String name, int size, String brand) {
        super(name, size, DEFAULT_PRICE, brand);
    }
}
