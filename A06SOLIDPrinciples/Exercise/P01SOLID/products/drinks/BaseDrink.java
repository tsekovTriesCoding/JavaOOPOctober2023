package A06SOLIDPrinciples.Exercise.P01SOLID.products.drinks;

public abstract class BaseDrink implements Drink {
    private double caloriesPer100Grams;
    private double density;
    private double milliliters;

    public BaseDrink(double caloriesPer100Grams, double density, double milliliters) {
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.density = density;
        this.milliliters = milliliters;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public double getCalories() {
        return (this.caloriesPer100Grams / 100) * (this.milliliters * this.density);
    }

    @Override
    public double getLiters() {
        return this.milliliters / 1000;
    }
}
