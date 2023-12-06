package restaurant.entities.healthyFoods;

public class Salad extends Food {
    public static final double DEFAULT_SALAD_PORTION = 150;

    public Salad(String name, double price) {
        super(name, DEFAULT_SALAD_PORTION, price);
    }
}
