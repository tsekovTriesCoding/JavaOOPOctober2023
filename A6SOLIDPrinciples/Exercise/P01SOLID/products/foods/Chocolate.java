package A6SOLIDPrinciples.Exercise.P01SOLID.products.foods;


public class Chocolate extends BaseFood {

    public static final double CALORIES_PER_100_GRAMS = 575.0;


    public Chocolate(double grams) {
        super(CALORIES_PER_100_GRAMS, grams);
    }
}
