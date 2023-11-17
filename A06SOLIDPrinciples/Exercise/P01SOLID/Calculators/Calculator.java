package A06SOLIDPrinciples.Exercise.P01SOLID.Calculators;


import A06SOLIDPrinciples.Exercise.P01SOLID.products.Product;

import java.util.List;

public interface Calculator {
    double sum(List<Product> products);
    double average(List<Product> products);
}
