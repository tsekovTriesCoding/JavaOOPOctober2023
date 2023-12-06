package christmasPastryShop.entities.booths.classes;

public class PrivateBooth extends BaseBooth {
    public static final double DEFAULT_PRICE_PER_PERSON = 3.5;

    public PrivateBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, DEFAULT_PRICE_PER_PERSON);

    }
}
