package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Aquarium aquarium;

    @Before
    public void init() {
        this.aquarium = new Aquarium("OutWorld", 5);
    }

    @Test
    public void testGetNameShouldReturnName() {
        Assert.assertEquals("OutWorld", this.aquarium.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        this.aquarium = new Aquarium(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        this.aquarium = new Aquarium("", 5);
    }

    @Test
    public void testGetCapacityShouldReturnCapacity() {
        Assert.assertEquals(5, this.aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.aquarium = new Aquarium("OutWorld", -5);
    }

    @Test
    public void testGetCountShouldReturnTheCountOfFishInAquarium() {
        Assert.assertEquals(0, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishShouldThrowExceptionOnNoRoomForFish() {
        this.aquarium.add(new Fish("Peter"));
        this.aquarium.add(new Fish("George"));
        this.aquarium.add(new Fish("John"));
        this.aquarium.add(new Fish("Nemo"));
        this.aquarium.add(new Fish("Lea"));
        this.aquarium.add(new Fish("Sky"));
    }

    @Test
    public void testAddFishShouldAddFish() {
        Assert.assertEquals(0, this.aquarium.getCount());
        this.aquarium.add(new Fish("Peter"));
        this.aquarium.add(new Fish("George"));

        Assert.assertEquals(2, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowExceptionOnMissingFish() {
        this.aquarium.add(new Fish("Peter"));
        this.aquarium.remove("George");
    }

    @Test
    public void testRemoveShouldRemoveFishFromAquarium() {
        this.aquarium.add(new Fish("Peter"));
        Assert.assertEquals(1, this.aquarium.getCount());

        this.aquarium.remove("Peter");
        Assert.assertEquals(0, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldThrowExceptionOnMissingFish() {
        this.aquarium.add(new Fish("Peter"));
        this.aquarium.sellFish("George");
    }

    @Test
    public void testSellFishShouldSellFish() {
        Fish fish = new Fish("Peter");
        this.aquarium.add(fish);
        Assert.assertTrue(fish.isAvailable());

        this.aquarium.sellFish("Peter");
        Assert.assertFalse(fish.isAvailable());
    }

    @Test
    public void testReportShouldReturnReportWithAllFishName() {
        this.aquarium.add(new Fish("Peter"));
        this.aquarium.add(new Fish("George"));
        this.aquarium.add(new Fish("John"));

        String actualReport = this.aquarium.report();
        String expectedReport = "Fish available at OutWorld: Peter, George, John";
        Assert.assertEquals(expectedReport, actualReport);
    }

    @Test
    public void testReportShouldReturnReportWithNoFishNames() {
        String actualReport = this.aquarium.report();
        String expectedReport = "Fish available at OutWorld: ";
        Assert.assertEquals(expectedReport, actualReport);
    }


}

