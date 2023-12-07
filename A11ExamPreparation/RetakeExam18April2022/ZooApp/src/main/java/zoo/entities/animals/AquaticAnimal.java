package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    public static final double DEFAULT_KILOGRAMS = 2.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KILOGRAMS, price);
    }

    @Override
    public void eat() {
        double newKilograms = super.getKg() + 7.50;
        super.setKg(newKilograms);
    }
}
