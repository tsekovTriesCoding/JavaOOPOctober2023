package A06SOLIDPrinciples.Exercise.P01SOLID.products.drinks;


import A06SOLIDPrinciples.Exercise.P01SOLID.products.Product;

public interface Drink extends Product {
    double getLiters();
    double getDensity();
}
