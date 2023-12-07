package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;

    @Before
    public void init() {
        this.spaceship = new Spaceship("Apollo", 3);
    }

    @Test
    public void testGEtCountShouldReturnTheCountOfAstronauts() {
        Assert.assertEquals(0, this.spaceship.getCount());
    }

    @Test
    public void testGetNameShouldReturnCorrectName() {
        Assert.assertEquals("Apollo", this.spaceship.getName());
    }

    @Test
    public void testGetCapacityShouldReturnCorrectCapacity() {
        Assert.assertEquals(3, this.spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionOnFullSpaceship() {
        this.spaceship.add(new Astronaut("Lee", 20.45));
        this.spaceship.add(new Astronaut("John", 12.67));
        this.spaceship.add(new Astronaut("Peter", 80.45));
        this.spaceship.add(new Astronaut("Travis", 56.91));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionOnExistingAstronaut() {
        this.spaceship.add(new Astronaut("Lee", 20.45));
        this.spaceship.add(new Astronaut("Lee", 12.67));
    }

    @Test
    public void testAddShouldAddCorrectly() {
        Assert.assertEquals(0, this.spaceship.getCount());
        this.spaceship.add(new Astronaut("Lee", 20.45));
        this.spaceship.add(new Astronaut("John", 12.67));

        Assert.assertEquals(2, this.spaceship.getCount());
    }

    @Test
    public void testRemoveShouldRemoveExistingAstronaut() {
        this.spaceship.add(new Astronaut("Lee", 20.45));
        Assert.assertEquals(1, this.spaceship.getCount());

        boolean isRemoved = this.spaceship.remove("Lee");
        Assert.assertTrue(isRemoved);
        Assert.assertEquals(0, this.spaceship.getCount());
    }

    @Test
    public void testRemoveShouldNotRemoveMissingAstronaut() {
        this.spaceship.add(new Astronaut("Lee", 20.45));
        Assert.assertEquals(1, this.spaceship.getCount());

        boolean isRemoved = this.spaceship.remove("Peter");
        Assert.assertFalse(isRemoved);
        Assert.assertEquals(1, this.spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacity() {
        this.spaceship = new Spaceship("Apollo", -2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        this.spaceship = new Spaceship(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        this.spaceship = new Spaceship("", 3);
    }
}
