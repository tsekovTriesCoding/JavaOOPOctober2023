package A04InterfacesAndAbstraction.Lab.P02CarShopExtended;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    public Seat(String model, String color, Integer horsePower, String countryProduced) {
        super(model, color, horsePower, countryProduced);
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s sells for %f", super.toString(), getModel(), getPrice());
    }
}
