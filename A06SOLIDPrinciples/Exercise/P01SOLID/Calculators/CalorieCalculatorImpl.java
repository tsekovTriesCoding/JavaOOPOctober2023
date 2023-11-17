package A06SOLIDPrinciples.Exercise.P01SOLID.Calculators;



import A06SOLIDPrinciples.Exercise.P01SOLID.products.Product;

import java.util.List;

public class CalorieCalculatorImpl implements Calculator {
    public CalorieCalculatorImpl() {
    }

    @Override
    public double sum(List<Product> products) {
        double sum = 0;

        for (Product product : products) {
            sum += product.getCalories();
        }

        return sum;
    }

    @Override
    public double average(List<Product> products) {
        return sum(products) / products.size();
    }

}
