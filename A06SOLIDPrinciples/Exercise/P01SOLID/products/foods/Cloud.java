package A06SOLIDPrinciples.Exercise.P01SOLID.products.foods;

public class Cloud extends BaseFood {

    // VIOLATION OF THE LISKOV SUBSTITUTION PRINCIPLE!!! Because the base class can't be replaced with the child class;
    //The cloud doesn't have grams or calories...

    public Cloud(double caloriesPer100Grams, double grams) {
        super(caloriesPer100Grams, grams);
    }

}
