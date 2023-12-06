package fairyShop.models.helpers;

public class Sleepy extends BaseHelper {
    public static final int DEFAULT_ENERGY = 50;

    public Sleepy(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void work() {
        super.setEnergy((Math.max(super.getEnergy() - 15, 0)));
    }
}
