package christmasPastryShop.entities.cocktails.classes;

public class MulledWine extends BaseCocktail {
    public static final double DEFAULT_PRICE = 3.5;

    public MulledWine(String name, int size, String brand) {
        super(name, size, DEFAULT_PRICE, brand);
    }
}
