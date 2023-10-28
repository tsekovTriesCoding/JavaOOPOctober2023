package A5Polymorphism.Exercise.P02VehiclesExtension;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
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
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

       double newFuel = liters + this.fuelQuantity;
       if (newFuel > this.tankCapacity) {
           throw new IllegalArgumentException("Cannot fit fuel in tank");
       }
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
