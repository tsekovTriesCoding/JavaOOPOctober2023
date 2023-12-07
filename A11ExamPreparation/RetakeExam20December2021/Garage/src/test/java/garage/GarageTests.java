package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;

    @Before
    public void init() {
        this.garage = new Garage();
    }

    @Test
    public void testGetCountShouldReturnTheCountOfCarsInGarage() {
        Assert.assertEquals(0, this.garage.getCount());
    }

    @Test
    public void testAddCarShouldAddCarToGarage() {
        Assert.assertEquals(0, this.garage.getCount());
        this.garage.addCar(new Car("BMW", 260, 10000));
        Assert.assertEquals(1, this.garage.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrowExceptionOnNullCar() {
        this.garage.addCar(null);
    }

    @Test
    public void testGetCarsShouldReturnListOfCars() {
        Assert.assertEquals(0, this.garage.getCount());
        this.garage.addCar(new Car("BMW", 260, 10000));

        List<Car> cars = this.garage.getCars();

        Assert.assertEquals(1, cars.size());
        Assert.assertEquals("BMW", cars.get(0).getBrand());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAboveShouldReturnWantedCarsList() {
        this.garage.addCar(new Car("BMW", 260, 10000));
        this.garage.addCar(new Car("Opel", 175, 2500));
        this.garage.addCar(new Car("VW", 200, 13456));

        List<Car> allCarsWithMaxSpeedAbove = this.garage.findAllCarsWithMaxSpeedAbove(175);
        Assert.assertEquals(2, allCarsWithMaxSpeedAbove.size());
        Assert.assertEquals("BMW", allCarsWithMaxSpeedAbove.get(0).getBrand());
        Assert.assertEquals("VW", allCarsWithMaxSpeedAbove.get(1).getBrand());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAboveShouldReturnEmptyList() {
        this.garage.addCar(new Car("BMW", 260, 10000));
        this.garage.addCar(new Car("Opel", 175, 2500));
        this.garage.addCar(new Car("VW", 200, 13456));

        List<Car> allCarsWithMaxSpeedAbove = this.garage.findAllCarsWithMaxSpeedAbove(260);
        Assert.assertEquals(0, allCarsWithMaxSpeedAbove.size());
    }

    @Test
    public void testGetTheMostExpensiveCarShouldReturnTheMostExpensiveCar() {
        this.garage.addCar(new Car("BMW", 260, 10000));
        this.garage.addCar(new Car("Opel", 175, 2500));
        Car car = new Car("VW", 200, 13456);
        this.garage.addCar(car);

        Car theMostExpensiveCar = this.garage.getTheMostExpensiveCar();
        Assert.assertEquals(theMostExpensiveCar, car);
    }

    @Test
    public void testFindAllCarsByBrandShouldReturnAllCarsByBrand() {
        this.garage.addCar(new Car("BMW", 260, 10000));
        this.garage.addCar(new Car("Opel", 175, 2500));
        this.garage.addCar(new Car("VW", 200, 13456));
        this.garage.addCar(new Car("Opel", 100, 1500));
        this.garage.addCar(new Car("Opel", 155, 8755));

        List<Car> opelCars = this.garage.findAllCarsByBrand("Opel");
        Assert.assertEquals(3, opelCars.size());
    }

    @Test
    public void testFindAllCarsByBrandShouldReturnEmptyListOnMissingBrand() {
        this.garage.addCar(new Car("BMW", 260, 10000));
        this.garage.addCar(new Car("Opel", 175, 2500));
        this.garage.addCar(new Car("VW", 200, 13456));

        List<Car> opelCars = this.garage.findAllCarsByBrand("Renault");
        Assert.assertEquals(0, opelCars.size());
    }
}