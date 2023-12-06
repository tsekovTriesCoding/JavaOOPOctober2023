package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food {
    public static final double DEFAULT_SALAD_PORTION = 205;

    public VeganBiscuits(String name, double price) {
        super(name, DEFAULT_SALAD_PORTION, price);
    }
}
