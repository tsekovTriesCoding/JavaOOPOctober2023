package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {
    public static final double DEFAULT_KILOGRAMS = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KILOGRAMS, price);
    }

    @Override
    public void eat() {
        double newKilograms = super.getKg() + 5.70;
        super.setKg(newKilograms);
    }
}
