package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTests {
    private Service service;

    @Before
    public void initializeService() {
        service = new Service("Ser", 10);
        service.add(new Robot("Tony"));
        service.add(new Robot("Peter"));
        service.add(new Robot("Gesh"));
        service.add(new Robot("Monica"));
    }

    @Test
    public void testGetNameShouldReturnServiceName() {
        Assert.assertEquals("Ser", this.service.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNameShouldThrowExceptionIfEmptyOrNull() {
        Service ser = new Service("", 5);
    }

    @Test
    public void testGetCapacityShouldReturnCorrectCapacity() {
        Assert.assertEquals(10, this.service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCapacityShouldThrowExceptionIfCapacityLessThanZero() {
        Service ser = new Service("Ser", -1);
    }

    @Test
    public void testGetCountShouldReturnCorrectRobotsCount() {
        Assert.assertEquals(4, this.service.getCount());
    }

    @Test
    public void testAddRobotShouldAddRobotCorrectly() {
        Assert.assertEquals(4, this.service.getCount());

        this.service.add(new Robot("New robot"));
        Assert.assertEquals(5, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotShouldThrowExceptionIfNoMoreCapacity() {
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
        this.service.add(new Robot("New robot"));
    }

    @Test
    public void testRemoveShouldRemoveRobotCorrectly() {
        Assert.assertEquals(4, this.service.getCount());

        this.service.remove("Tony");
        Assert.assertEquals(3, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowExceptionOnMissingRobot() {
        this.service.remove("No robot");
    }

    @Test
    public void testForSaleShouldSetRobotReadyToSaleToFalse() {
        Robot robot = this.service.forSale("Gesh");
        Assert.assertFalse(robot.isReadyForSale());
    }

    @Test
    public void testForSaleShouldReturnTheRobotForSale() {
        Robot robot = this.service.forSale("Gesh");
        Assert.assertEquals("Gesh", robot.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForSaleShouldThrowExceptionOnMissingRobot() {
        Robot robot = this.service.forSale("Missing robot");

    }

    @Test
    public void testReportShouldReturnAllRobotsInService() {
        String expectedReport = "The robot Tony, Peter, Gesh, Monica is in the service Ser!";
        String actualReport = this.service.report();
        Assert.assertEquals(expectedReport, actualReport);
    }


}
