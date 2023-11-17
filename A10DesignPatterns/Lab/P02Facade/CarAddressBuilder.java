package A10DesignPatterns.Lab.P02Facade;

public class CarAddressBuilder extends CarBuilderFacade {
    public CarAddressBuilder(Car car) {
        this.car = car;
    }

    public CarAddressBuilder inCity(String city) {
        car.setCity(city);
        return this;
    }

    public CarAddressBuilder atAddress(String address) {
        car.setAddress(address);
        return this;
    }
}
