package A3Inheritance.Exercise.P05Restaurant.Beverage;

import A3Inheritance.Exercise.P05Restaurant.Product;

import java.math.BigDecimal;

public class Beverage extends Product {
    private double milliliters;

    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
