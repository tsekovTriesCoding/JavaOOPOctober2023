package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private House house;

    @Before
    public void init() {
        this.house = new House("Baraka", 2);
    }

    @Test
    public void testGetNameShouldReturnName() {
        Assert.assertEquals("Baraka", this.house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        this.house = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        this.house = new House("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.house = new House("Baraka", -10);
    }

    @Test
    public void testGetCapacityShouldReturnCapacity() {
        Assert.assertEquals(2, this.house.getCapacity());
    }

    @Test
    public void testGetCountShouldReturnCountOfCatsInHouse() {
        Assert.assertEquals(0, this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldThrowExceptionOnNoRoomToAddCat() {
        this.house.addCat(new Cat("Tom"));
        this.house.addCat(new Cat("John"));
        this.house.addCat(new Cat("Peter"));
    }

    @Test
    public void testAddCatShouldAdd() {
        Assert.assertEquals(0, this.house.getCount());
        this.house.addCat(new Cat("Tom"));
        Assert.assertEquals(1, this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatShouldThrowExceptionOnMissingCat() {
        this.house.addCat(new Cat("Tom"));
        this.house.removeCat("No");
    }

    @Test
    public void testRemoveCatShouldRemoveCat() {
        this.house.addCat(new Cat("Tom"));
        Assert.assertEquals(1, this.house.getCount());

        this.house.removeCat("Tom");
        Assert.assertEquals(0, this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldThrowExceptionOnMissingCat() {
        this.house.addCat(new Cat("Tom"));
        Cat cat = this.house.catForSale("No");
    }

    @Test
    public void testCatForSaleShouldReturnCat() {
        Cat cat = new Cat("Tom");
        this.house.addCat(cat);
        Assert.assertTrue(cat.isHungry());

        Cat catForSale = this.house.catForSale("Tom");
        Assert.assertEquals(catForSale, catForSale);
        Assert.assertFalse(catForSale.isHungry());
    }

    @Test
    public void testStatisticsShouldReturnCorrectStatistics() {
        this.house.addCat(new Cat("Tom"));
        this.house.addCat(new Cat("John"));

        String actualStatistics = this.house.statistics();
        String expectedStatistics = "The cat Tom, John is in the house Baraka!";

        Assert.assertEquals(expectedStatistics, actualStatistics);
    }


}
