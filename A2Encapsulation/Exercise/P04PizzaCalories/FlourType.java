package A2Encapsulation.Exercise.P04PizzaCalories;

public enum FlourType {
    White(1.5),
    Wholegrain(1);

    private final double modifier;

    FlourType(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
