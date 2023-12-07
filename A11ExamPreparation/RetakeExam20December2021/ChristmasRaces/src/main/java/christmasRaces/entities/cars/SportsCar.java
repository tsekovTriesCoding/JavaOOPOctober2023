package christmasRaces.entities.cars;

public class SportsCar extends BaseCar {
    public static final double DEFAULT_CUBIC_CENTIMETERS = 3000;
    public static final int MINIMUM_HP = 250;
    public static final int MAXIMUM_HP = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    @Override
    protected boolean isOutOfRange(int horsePower) {
        return horsePower < MINIMUM_HP || horsePower > MAXIMUM_HP;
    }

}
