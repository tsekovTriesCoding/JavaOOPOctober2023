package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    public static final int DEFAULT_STRENGTH = 70;

    public SecondShift(String name) {
        super(name, DEFAULT_STRENGTH);
    }

    @Override
    public void working() {
        super.setStrength(Math.max(super.getStrength() - 15, 0));
    }
}
