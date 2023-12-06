package onlineShop.models.products.components;

public class PowerSupply extends BaseComponent {
    public static final double PERFORMANCE_MULTIPLIER = 1.05;
    public PowerSupply(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * PERFORMANCE_MULTIPLIER, generation);
    }
}
