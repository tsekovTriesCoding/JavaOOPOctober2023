package A06SOLIDPrinciples.Exercise.P01SOLID.products.drinks;

public class Lemonade extends BaseDrink {

    public static final double CALORIES_PER_100_GRAMS = 53.0;
    public static final double DENSITY = 0.7;


    public Lemonade(double milliliters) {
        super(CALORIES_PER_100_GRAMS, DENSITY, milliliters);
    }
}
