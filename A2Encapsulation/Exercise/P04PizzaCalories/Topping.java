package A2Encapsulation.Exercise.P04PizzaCalories;

import java.util.Map;

public class Topping {

    private static final Map<String,Double> toppingsMap = Map.of("Meat", 1.2,
           "Veggies", 0.8,
            "Cheese", 1.1,
            "Sauce", 0.9);
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        return (2 * this.weight) * toppingsMap.get(this.toppingType);
    }

    private void setToppingType(String toppingType) {
        if (!toppingsMap.containsKey(toppingType)) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }

        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }

}
