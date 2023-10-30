package A6SOLIDPrinciples.Exercise.P01SOLID.products.foods;


public class Chips extends BaseFood {
    public static final double CALORIES_PER_100_GRAMS = 529.0;

    public Chips(double grams) {
        super(CALORIES_PER_100_GRAMS, grams);
    }
}
