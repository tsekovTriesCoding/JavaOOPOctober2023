package robotService.entities.robot;

public class MaleRobot extends BaseRobot {
    public static final int INITIAL_KILOGRAMS = 9;
    public static final int KILOGRAMS_INCREASE_FACTOR = 3;

    public MaleRobot(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAMS, price);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + KILOGRAMS_INCREASE_FACTOR;
        super.setKilograms(newKilograms);
    }
}
