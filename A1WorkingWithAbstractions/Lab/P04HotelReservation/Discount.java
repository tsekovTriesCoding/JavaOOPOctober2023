package A1WorkingWithAbstraction.Lab.P04HotelReservation;

public enum Discount {
    VIP(0.2), SecondVisit(0.1), None(0);

    private final double discountPercent;

    Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
