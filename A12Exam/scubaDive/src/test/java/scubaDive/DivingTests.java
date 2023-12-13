package scubaDive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivingTests {
    private Diving diving;

    @Before
    public void init() {
        this.diving = new Diving("Mariana Trench", 5);
    }

    @Test
    public void testGetCountShouldReturnTheCountOfDivers() {
        Assert.assertEquals(0, this.diving.getCount());
    }

    @Test
    public void testGetNameShouldReturnName() {
        Assert.assertEquals("Mariana Trench", this.diving.getName());
    }

    @Test
    public void testGetCapacityShouldReturnCapacity() {
        Assert.assertEquals(5, this.diving.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.diving = new Diving("Mariana Trench", -5);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        this.diving = new Diving(null, 5);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        this.diving = new Diving("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverShouldThrowExceptionOnFullCapacity() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 22.6));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("John", 150));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Peter", 67.8));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Maria", 12));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("juan", 67));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Antonio", 8.9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverShouldThrowExceptionWhenAddingExistingDiver() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 22.6));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 150));
    }

    @Test
    public void testAddDeepWaterDiverShouldAddDiverCorrectly() {
        Assert.assertEquals(0, this.diving.getCount());
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 22.6));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("John", 150));

        Assert.assertEquals(2, this.diving.getCount());
    }

    @Test
    public void testRemoveDeepWaterDiverShouldRemoveDiver() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 22.6));
        Assert.assertEquals(1, this.diving.getCount());

        boolean isRemoved = this.diving.removeDeepWaterDiver("Tom");
        Assert.assertEquals(0, this.diving.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testRemoveDeepWaterDiverShouldNotRemoveMissingDiver() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Tom", 22.6));
        Assert.assertEquals(1, this.diving.getCount());

        boolean isRemoved = this.diving.removeDeepWaterDiver("John");
        Assert.assertEquals(1, this.diving.getCount());
        Assert.assertFalse(isRemoved);
    }




}
