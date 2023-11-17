package A05Polymorphism.Exercise.P02VehiclesExtension;


public class Car extends VehicleImpl {

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.setFuelConsumption(fuelConsumption + 0.9);
    }

}
