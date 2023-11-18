package robotService.entities.robot;

public class FemaleRobot extends BaseRobot {
    public static final int INITIAL_KILOGRAMS = 7;
    public static final int KILOGRAMS_INCREASE_FACTOR = 1;
    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAMS, price);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + KILOGRAMS_INCREASE_FACTOR;
        super.setKilograms(newKilograms);
    }
}
