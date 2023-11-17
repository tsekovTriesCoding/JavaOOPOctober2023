package A05Polymorphism.Exercise.P01Vehicles;


public class Car extends VehicleImpl {

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        this.setFuelConsumption(fuelConsumption + 0.9);
    }

}
