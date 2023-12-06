package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    private ComputerManager computerManager;

    @Before
    public void init() {
        this.computerManager = new ComputerManager();
    }

    @Test
    public void testGetComputersShouldReturnListOfComputers() {
        Assert.assertTrue(this.computerManager.getComputers().isEmpty());
    }

    @Test
    public void testGetCountShouldReturnYheCountOfComputers() {
        Assert.assertEquals(0, this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldThrowExceptionWhenTryingToAddNullComputer() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldThrowExceptionWhenTryingToAddExistingComputer() {
        Computer computer1 = new Computer("IBM", "B4a", 120.45);
        Computer computer2 = new Computer("IBM", "B4a", 234.89);

        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
    }

    @Test
    public void testAddComputerShouldAddCorrectly() {
        Assert.assertEquals(0, this.computerManager.getCount());
        Computer computer = new Computer("IBM", "B4a", 120.45);
        this.computerManager.addComputer(computer);

        Assert.assertEquals(1, this.computerManager.getCount());
    }

    @Test
    public void testRemoveComputerShouldRemoveExistingComputer() {
        Computer computer = new Computer("IBM", "B4a", 120.45);
        this.computerManager.addComputer(computer);
        Assert.assertEquals(1, this.computerManager.getCount());

        Computer removedComputer = this.computerManager.removeComputer("IBM", "B4a");
        Assert.assertEquals(0, this.computerManager.getCount());
        Assert.assertEquals(computer, removedComputer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerGetComputerShouldThrowExceptionOnNullManufacturer() {
        this.computerManager.getComputer(null, "Some");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerGetComputerShouldThrowExceptionOnNullModel() {
        this.computerManager.getComputer("Some", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerGetComputerShouldThrowExceptionOnMissingComputer() {
        Computer computer = new Computer("IBM", "B4a", 120.45);
        this.computerManager.addComputer(computer);

        Computer comp = this.computerManager.getComputer("ACER", "Predator");
    }

    @Test
    public void testGetComputerShouldReturnExistingComputer() {
        Computer computer = new Computer("IBM", "B4a", 120.45);
        this.computerManager.addComputer(computer);

        Computer comp = this.computerManager.getComputer("IBM", "B4a");
        Assert.assertEquals(computer.getManufacturer(), comp.getManufacturer());
        Assert.assertEquals(computer.getModel(), comp.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersByManufacturerShouldThrowExceptionOnNullManufacturer() {
        this.computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputersByManufacturerShouldReturnListOfComputersByGivenManufacturer() {
        Computer computer1 = new Computer("IBM", "B4a", 120.45);
        Computer computer2 = new Computer("IBM", "500", 200);
        Computer computer3 = new Computer("IBM", "Old", 1236.80);
        Computer computer4 = new Computer("Acer", "Predator", 3300.90);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);
        this.computerManager.addComputer(computer4);

        List<Computer> expectedComputers = List.of(computer1, computer2, computer3);

        List<Computer> actualComputers = this.computerManager.getComputersByManufacturer("IBM");
        Assert.assertEquals(3, actualComputers.size());

        for (int i = 0; i < expectedComputers.size(); i++) {
            Computer expectedComputer = expectedComputers.get(0);
            Computer actualComputer = actualComputers.get(0);
            Assert.assertEquals(expectedComputer.getManufacturer(), actualComputer.getManufacturer());
            Assert.assertEquals(expectedComputer.getModel(), actualComputer.getModel());
        }
    }

}