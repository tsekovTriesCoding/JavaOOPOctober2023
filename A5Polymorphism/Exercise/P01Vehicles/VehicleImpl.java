package A5Polymorphism.Exercise.P01Vehicles;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public VehicleImpl(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = this.getFuelConsumption() * distance;

        String result = String.format("%s needs refueling", this.getClass().getSimpleName());
        DecimalFormat df = new DecimalFormat("#.##");
        if (fuelNeeded <= this.getFuelQuantity()) {
            this.fuelQuantity -= fuelNeeded;
            result = String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        }

        return result;
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
