package A6SOLIDPrinciples.Exercise.P01SOLID.products.drinks;

public class Coke extends BaseDrink {

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    public static final double DENSITY = 0.6;

    public Coke(double milliliters) {
        super(CALORIES_PER_100_GRAMS, DENSITY, milliliters);
    }
}
