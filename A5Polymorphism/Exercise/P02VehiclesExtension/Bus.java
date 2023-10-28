package A5Polymorphism.Exercise.P02VehiclesExtension;

public class Bus extends VehicleImpl {
    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String drive(double distance) {
        if (this.isEmpty) {
            this.setEmpty(false);
            super.setFuelConsumption(super.getFuelConsumption() + 1.4);
        }

        return super.drive(distance);
    }

    public String driveEmpty(double distance) {
        if (!isEmpty) {
            this.setEmpty(true);
            super.setFuelConsumption(super.getFuelConsumption() - 1.4);
        }

        return super.drive(distance);
    }
}
