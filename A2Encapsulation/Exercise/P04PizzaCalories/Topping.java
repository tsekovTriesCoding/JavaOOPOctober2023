package A2Encapsulation.Exercise.P04PizzaCalories;

import java.util.Arrays;

public class Topping {
    private ToppingType toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        return (2 * this.weight) * this.toppingType.getModifier();
    }

    private void setToppingType(String toppingType) {
        boolean toppingExists = (Arrays.stream(ToppingType.values()).anyMatch(e -> e.name().equals(toppingType)));

        if (!toppingExists) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }

        this.toppingType = ToppingType.valueOf(toppingType);

    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }

}
