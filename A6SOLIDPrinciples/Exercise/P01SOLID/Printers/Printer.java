package A6SOLIDPrinciples.Exercise.P01SOLID.Printers;



import A6SOLIDPrinciples.Exercise.P01SOLID.Calculators.Calculator;
import A6SOLIDPrinciples.Exercise.P01SOLID.products.Product;

import java.util.List;

public class Printer {
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";

    private Calculator calculator;

    public void printSum(List<Product> products) {
        System.out.printf((SUM) + "%n", calculator.sum(products));
    }

    public void printAverage(List<Product> products) {
        System.out.printf((AVERAGE) + "%n", calculator.average(products));
    }
}
