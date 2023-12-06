package christmasPastryShop.entities.booths.classes;

public class OpenBooth extends BaseBooth {
    public static final double DEFAULT_PRICE_PER_PERSON = 2.5;

    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, DEFAULT_PRICE_PER_PERSON);

    }
}
