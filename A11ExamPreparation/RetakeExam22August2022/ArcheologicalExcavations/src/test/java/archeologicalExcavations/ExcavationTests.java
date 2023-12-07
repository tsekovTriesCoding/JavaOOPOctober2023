package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

public class ExcavationTests {
    private Excavation excavation;

    @Test
    public void testGetCountShoutReturnTheNumberOfArchaeologistsOTheExcavation() {
        this.excavation = new Excavation("Lordaeron", 3);
        Assert.assertEquals(0, this.excavation.getCount());
    }

    @Test
    public void testGetNameShouldReturnName() {
        this.excavation = new Excavation("Lordaeron", 3);
        Assert.assertEquals("Lordaeron", this.excavation.getName());
    }

    @Test
    public void testGetCapacityShouldReturnTheCapacity() {
        this.excavation = new Excavation("Lordaeron", 3);
        Assert.assertEquals(3, this.excavation.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.excavation = new Excavation("Some", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        this.excavation = new Excavation(null, 2);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        this.excavation = new Excavation("", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistsShouldThrowExceptionIfCapacityIsFull() {
        this.excavation = new Excavation("Lordaeron", 3);
        this.excavation.addArchaeologist(new Archaeologist("John", 20));
        this.excavation.addArchaeologist(new Archaeologist("Timmy", 12));
        this.excavation.addArchaeologist(new Archaeologist("Peter", 12));
        this.excavation.addArchaeologist(new Archaeologist("Jack", 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistsShouldThrowExceptionOnAddingExistingArchaeologists() {
        this.excavation = new Excavation("Lordaeron", 3);
        this.excavation.addArchaeologist(new Archaeologist("Timmy", 12));
        this.excavation.addArchaeologist(new Archaeologist("Timmy", 12));
    }

    @Test
    public void testAddArchaeologistsShouldAdd() {
        this.excavation = new Excavation("Lordaeron", 3);
        Assert.assertEquals(0, this.excavation.getCount());
        this.excavation.addArchaeologist(new Archaeologist("Peter", 12));

        Assert.assertEquals(1, this.excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistShouldRemoveExistingArchaeologist() {
        this.excavation = new Excavation("Lordaeron", 3);
        this.excavation.addArchaeologist(new Archaeologist("John", 20));
        this.excavation.addArchaeologist(new Archaeologist("Timmy", 12));

        Assert.assertEquals(2, this.excavation.getCount());
        boolean isRemoved = this.excavation.removeArchaeologist("Timmy");
        Assert.assertEquals(1, this.excavation.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testRemoveArchaeologistShouldNotRemoveMissingArchaeologist() {
        this.excavation = new Excavation("Lordaeron", 3);
        this.excavation.addArchaeologist(new Archaeologist("John", 20));
        this.excavation.addArchaeologist(new Archaeologist("Timmy", 12));

        Assert.assertEquals(2, this.excavation.getCount());
        boolean isRemoved = this.excavation.removeArchaeologist("Missing");
        Assert.assertEquals(2, this.excavation.getCount());
        Assert.assertFalse(isRemoved);
    }


}
