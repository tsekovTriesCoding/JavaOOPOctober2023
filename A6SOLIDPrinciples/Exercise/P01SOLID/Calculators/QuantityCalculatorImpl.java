package A6SOLIDPrinciples.Exercise.P01SOLID.Calculators;



import A6SOLIDPrinciples.Exercise.P01SOLID.products.Product;
import A6SOLIDPrinciples.Exercise.P01SOLID.products.drinks.Drink;
import A6SOLIDPrinciples.Exercise.P01SOLID.products.foods.Food;

import java.util.List;

public class QuantityCalculatorImpl implements Calculator {
    public QuantityCalculatorImpl() {
    }

    @Override
    public double sum(List<Product> products) {
        double sum = 0;

        for (Product product : products) {
            if (product instanceof Food food) {
                sum += food.getKilograms();
            } else if (product instanceof Drink drink) {
                sum += drink.getLiters() * drink.getDensity();
            }
        }

        return sum;
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
