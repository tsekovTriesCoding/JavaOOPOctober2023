package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Farm farm;

    @Before
    public void init() {
        this.farm = new Farm("Happy Farm", 3);
    }

    @Test
    public void testGetCountShouldReturnTheCountOfAnimalsInTheFarm() {
        Assert.assertEquals(0, this.farm.getCount());
    }

    @Test
    public void testGetNAmeShouldReturnName() {
        Assert.assertEquals("Happy Farm", this.farm.getName());
    }

    @Test
    public void testGetCapacityShouldReturnTheCapacityOfTheFarm() {
        Assert.assertEquals(3, this.farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionWhenFarmIsFull() {
        this.farm.add(new Animal("Cow", 20));
        this.farm.add(new Animal("Horse", 31));
        this.farm.add(new Animal("Chicken", 12));
        this.farm.add(new Animal("Pig", 7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionWhenAnimalAlreadyExistsInFarm() {
        this.farm.add(new Animal("Cow", 20));
        this.farm.add(new Animal("Cow", 31));
    }

    @Test
    public void testAddShouldAddAnimal() {
        Assert.assertEquals(0, this.farm.getCount());
        this.farm.add(new Animal("Cow", 20));
        Assert.assertEquals(1, this.farm.getCount());

        this.farm.add(new Animal("Horse", 31));
        Assert.assertEquals(2, this.farm.getCount());
    }

    @Test
    public void testRemoveShouldRemoveExistingAnimal() {
        this.farm.add(new Animal("Cow", 20));
        this.farm.add(new Animal("Horse", 31));
        Assert.assertEquals(2, this.farm.getCount());

        boolean isRemoved = this.farm.remove("Horse");
        Assert.assertTrue(isRemoved);
        Assert.assertEquals(1, this.farm.getCount());
    }

    @Test
    public void testRemoveShouldNotWorkWhenAnimalDoesNotExist() {
        this.farm.add(new Animal("Cow", 20));
        this.farm.add(new Animal("Horse", 31));
        Assert.assertEquals(2, this.farm.getCount());

        boolean isRemoved = this.farm.remove("Pig");
        Assert.assertFalse(isRemoved);
        Assert.assertEquals(2, this.farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.farm = new Farm("Happy Farm", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullNAme() {
        this.farm = new Farm(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyNAme() {
        this.farm = new Farm("", 3);
    }

}
