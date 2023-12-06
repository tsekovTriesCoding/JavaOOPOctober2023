package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CarShopTests {
    private CarShop carShop;

    @Before
    public void initializeCarShop() {
        this.carShop = new CarShop();

        this.carShop.add(new Car("BMW", 100, 15000.45));
        this.carShop.add(new Car("Mercedes", 250, 25678.86));
        this.carShop.add(new Car("VW", 150, 12000));
    }

    @Test
    public void testGetCarsShouldReturnListOfCars() {
        Assert.assertEquals(3, this.carShop.getCars().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCarsShouldNotAllowToAddNewCarsToList() {
        List<Car> cars = this.carShop.getCars();

        cars.add(new Car("VW", 150, 12000));
    }

    @Test
    public void testGetCountShouldReturnCorrectCountOfCarsInShop() {
        Assert.assertEquals(3, this.carShop.getCount());
    }

    @Test
    public void testFindAllCarsWithMaxHorsePowerShouldReturnCarsWithMaxPower() {
        List<Car> allCarsWithMaxHorsePower = this.carShop.findAllCarsWithMaxHorsePower(100);
        Assert.assertEquals(2, allCarsWithMaxHorsePower.size());

        Car firstCar = allCarsWithMaxHorsePower.get(0);
        Car secondCar = allCarsWithMaxHorsePower.get(1);

        Assert.assertEquals(firstCar, this.carShop.getCars().get(1));
        Assert.assertEquals(secondCar, this.carShop.getCars().get(2));
    }

    @Test(expected = NullPointerException.class)
    public void testAddCarShouldThrowExceptionOnNullCar() {
        this.carShop.add(null);
    }

    @Test
    public void testAddCarShouldAddCar() {
        Assert.assertEquals(3, this.carShop.getCount());
        this.carShop.add(new Car("Opel", 75, 7000));

        Assert.assertEquals(4, this.carShop.getCount());
        Assert.assertEquals("Opel",this.carShop.getCars().get(3).getModel());
    }

    @Test
    public void testRemoveCarShouldRemoveCarAndReturnTrueIfRemoved() {
        Car car = this.carShop.getCars().get(0);
        Assert.assertTrue(this.carShop.remove(car));
    }

    @Test
    public void testRemoveCarShouldNotRemoveCarIfMissingAnReturnFalse() {
        Car car = new Car("Zastava", 50, 12000);
        Assert.assertFalse(this.carShop.remove(car));
    }

    @Test
    public void testGetTheMostLuxuryCarShouldReturnTheCorrectCar() {
        Car theMostLuxuryCar = this.carShop.getTheMostLuxuryCar();

        Assert.assertEquals(theMostLuxuryCar, this.carShop.getCars().get(1));
    }

    @Test
    public void testFindAllCarByModelShouldReturnAllCarsWithGivenModel() {
        this.carShop.add(new Car("BMW", 263, 22768));

        List<Car> bmwCars = this.carShop.findAllCarByModel("BMW");

        Assert.assertEquals(2, bmwCars.size());
    }

}

