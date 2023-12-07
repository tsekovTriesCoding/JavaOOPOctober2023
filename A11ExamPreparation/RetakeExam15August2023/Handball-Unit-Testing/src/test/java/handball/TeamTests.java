package handball;

import org.junit.Assert;
import org.junit.Test;

public class TeamTests {
    private Team team;

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullName() {
        team = new Team(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyName() {
        team = new Team("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnPositionLessThanZero() {
        team = new Team("Levski", -1);
    }

    @Test
    public void testGetNameShouldReturnTeamName() {
        team = new Team("Levski", 1);
        Assert.assertEquals("Levski", team.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNameShouldThrowExceptionOnNull() {
        String name = team.getName();
    }

    @Test
    public void testGetPositionShouldReturnCorrectTeamPosition() {
        team = new Team("Levski", 1);
        Assert.assertEquals(1, team.getPosition());
    }

    @Test
    public void testGetCountShouldReturnCountOfPlayers() {
        team = new Team("Levski", 1);
        Assert.assertEquals(0, team.getCount());
    }

    @Test
    public void testAddShouldAddPlayerToTeam() {
        team = new Team("Levski", 1);
        team.add(new HandballPlayer("Ivan"));
        Assert.assertEquals(1, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowExceptionIfNoRoomForNewPlayer() {
        team = new Team("Levski", 1);
        team.add(new HandballPlayer("Ivan"));
        team.add(new HandballPlayer("Ivan"));
    }

    @Test
    public void testRemoveShouldRemovePlayerFromTeam() {
        team = new Team("Levski", 1);
        team.add(new HandballPlayer("Ivan"));
        Assert.assertEquals(1, team.getCount());

        team.remove("Ivan");
        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowExceptionOnMissingPlayer() {
        team = new Team("Levski", 1);
        team.add(new HandballPlayer("Ivan"));

        team.remove("MissingPlayer");
    }

    @Test
    public void testPlayerForAnotherTeamShouldReturnThePlayerIfFound() {
        team = new Team("Levski", 1);
        HandballPlayer actualPlayer = new HandballPlayer("Ivan");
        team.add(actualPlayer);

        HandballPlayer expected = team.playerForAnotherTeam("Ivan");
        Assert.assertEquals(expected, actualPlayer);
    }

    @Test
    public void testPlayerForAnotherTeamShouldSetTheGivenPlayerActiveStatusToFalse() {
        team = new Team("Levski", 1);
        HandballPlayer actualPlayer = new HandballPlayer("Ivan");
        team.add(actualPlayer);

        HandballPlayer expected = team.playerForAnotherTeam("Ivan");
        Assert.assertFalse(expected.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamShouldThrowExceptionOnMissingPlayer() {
        team = new Team("Levski", 1);
        team.playerForAnotherTeam("Ivan");
    }

    @Test
    public void testGetStatisticsShouldReturnTextWithAllPlayersNamesSeparatedBWithComasNAdSpace() {
        team = new Team("Levski", 4);
        HandballPlayer player1 = new HandballPlayer("Ivan");
        HandballPlayer player2 = new HandballPlayer("Tony");
        HandballPlayer player3 = new HandballPlayer("Peter");
        HandballPlayer player4 = new HandballPlayer("George");

        team.add(player1);
        team.add(player2);
        team.add(player3);
        team.add(player4);

        String expectedResult = "The player Ivan, Tony, Peter, George is in the team Levski.";
        String actualResult = team.getStatistics();

        Assert.assertEquals(expectedResult, actualResult);
    }


}
