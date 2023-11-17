package A03Inheritance.Exercise.P05Restaurant;

import A03Inheritance.Exercise.P05Restaurant.Beverage.Coffee;
import A03Inheritance.Exercise.P05Restaurant.Food.Cake;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new Coffee("Bianchi", 20);
        System.out.println(coffee.getCaffeine());
        System.out.println(coffee.getMilliliters());

        Cake cake = new Cake("Saher");
        System.out.println(cake.getGrams());
        System.out.println(cake.getCalories());
        System.out.println(cake.getPrice());
    }
}
