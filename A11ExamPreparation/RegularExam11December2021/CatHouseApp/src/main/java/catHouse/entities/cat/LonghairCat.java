package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    public static final int DEFAULT_KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }


    @Override
    public void setKilograms(int kilograms) {
        super.setKilograms(DEFAULT_KILOGRAMS);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + 3;
        super.setKilograms(newKilograms);
    }
}
