package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    public static final int DEFAULT_STRENGTH = 70;
    public SecondShift(String name) {
        super(name, DEFAULT_STRENGTH);
    }

    @Override
    public void working() {
        int newStrength = super.getStrength() - 5;
        if (newStrength < 0) {
            newStrength = 0;
        }
        super.setStrength(newStrength);
    }
}
