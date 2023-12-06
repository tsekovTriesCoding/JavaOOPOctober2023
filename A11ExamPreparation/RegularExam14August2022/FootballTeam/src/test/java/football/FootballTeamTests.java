package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballTeamTests {
    private FootballTeam footballTeam;

    @Test
    public void testGetNameShouldReturnName() {
        footballTeam = new FootballTeam("Levski", 11);
        Assert.assertEquals("Levski", footballTeam.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        footballTeam = new FootballTeam(null, 11);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        footballTeam = new FootballTeam("", 11);
    }

    @Test
    public void testGetVacantPositionsShouldReturnVacantPositions() {
        footballTeam = new FootballTeam("Levski", 11);
        Assert.assertEquals(11, footballTeam.getVacantPositions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeVacantPositionsValue() {
        footballTeam = new FootballTeam("Levski", -11);
    }

    @Test
    public void testGetCountShouldReturnTheNumberOfPlayersInTeam() {
        footballTeam = new FootballTeam("Levski", 11);
        Assert.assertEquals(0, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerShouldThrowExceptionIfNoRoomForMore() {
        footballTeam = new FootballTeam("Levski", 1);
        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.addFootballer(new Footballer("Petrov"));
    }

    @Test
    public void testAddFootballerShouldAdd() {
        footballTeam = new FootballTeam("Levski", 11);
        Assert.assertEquals(0, footballTeam.getCount());

        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.addFootballer(new Footballer("IPetrov"));
        Assert.assertEquals(2, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerShouldThrowExceptionOnMissingPlayer() {
        footballTeam = new FootballTeam("Levski", 11);
        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.removeFootballer("Petrov");

    }

    @Test
    public void testRemoveFootballerShouldThrowRemovePlayerFromTeam() {
        footballTeam = new FootballTeam("Levski", 11);
        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.addFootballer(new Footballer("Petrov"));
        Assert.assertEquals(2, footballTeam.getCount());

        footballTeam.removeFootballer("Petrov");
        Assert.assertEquals(1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleShouldThrowExceptionOnMissingPlayer() {
        footballTeam = new FootballTeam("Levski", 11);
        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.footballerForSale("Petrov");
    }

    @Test
    public void testFootballerForSaleShouldReturnGivenPlayer() {
        footballTeam = new FootballTeam("Levski", 11);
        Footballer footballer = new Footballer("Ivanov");
        footballTeam.addFootballer(footballer);
        Footballer playerForSale = footballTeam.footballerForSale("Ivanov");

        Assert.assertFalse(playerForSale.isActive());
        Assert.assertEquals(playerForSale, footballer);
    }

    @Test
    public void testGetStatisticsShouldReturnTextWithAllPlayersNames() {
        footballTeam = new FootballTeam("Levski", 11);
        footballTeam.addFootballer(new Footballer("Ivanov"));
        footballTeam.addFootballer(new Footballer("Petrov"));

        String expectedResult = "The footballer Ivanov, Petrov is in the team Levski.";
        String actualResult = footballTeam.getStatistics();
        Assert.assertEquals(expectedResult, actualResult);
    }


}
