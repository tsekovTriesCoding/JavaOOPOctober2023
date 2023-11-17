package A06SOLIDPrinciples.Exercise.P01SOLID.products.foods;


public abstract class BaseFood implements Food {
    private double caloriesPer100Grams;
    private double grams;

    public BaseFood(double caloriesPer100Grams, double grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.grams = grams;
    }

    @Override
    public double getCalories() {
        return (this.caloriesPer100Grams / 100) * this.grams;
    }

    @Override
    public double getKilograms() {
        return this.grams / 1000;
    }
}
