package easterRaces.entities.cars;

public class MuscleCar extends BaseCar {
    public static final double DEFAULT_CUBIC_CENTIMETERS = 5000;
    public static final int MINIMUM_HP = 400;
    public static final int MAXIMUM_HP = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    @Override
    protected boolean isOutOfRange(int horsePower) {
        return horsePower < MINIMUM_HP || horsePower > MAXIMUM_HP;
    }

}
