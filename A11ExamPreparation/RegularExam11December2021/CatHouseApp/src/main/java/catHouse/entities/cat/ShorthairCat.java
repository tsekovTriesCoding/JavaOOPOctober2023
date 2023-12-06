package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {
    public static final int DEFAULT_KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
    }


    @Override
    public void setKilograms(int kilograms) {
        super.setKilograms(DEFAULT_KILOGRAMS);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + 1;
        super.setKilograms(newKilograms);
    }
}
