package bank;

import org.junit.Assert;
import org.junit.Test;

public class BankTests {
    private Bank bank;

    @Test
    public void testGetNameShouldReturnBankName() {
        bank = new Bank("DSK", 100);
        Assert.assertEquals("DSK", bank.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnNullBankName() {
        bank = new Bank(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowExceptionOnEmptyBankName() {
        bank = new Bank("", 100);
    }

    @Test
    public void testGetCapacityShouldReturnBankCapacity() {
        bank = new Bank("DSK", 100);
        Assert.assertEquals(100, bank.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowExceptionOnNegativeCapacityValue() {
        bank = new Bank("DSK", -1);
    }

    @Test
    public void testGetCountShouldReturnTheCountOfClientsInTheBank() {
        bank = new Bank("DSK", 100);
        bank.addClient(new Client("Tom"));
        bank.addClient(new Client("John"));
        bank.addClient(new Client("Peter"));

        Assert.assertEquals(3, bank.getCount());
    }

    @Test
    public void testAddClientShouldAddClientToTheBank() {
        bank = new Bank("DSK", 100);
        Assert.assertEquals(0, bank.getCount());

        bank.addClient(new Client("Tom"));
        Assert.assertEquals(1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientShouldThrowExceptionIfNoCapacityForClient() {
        bank = new Bank("DSK", 1);
        bank.addClient(new Client("Tom"));
        bank.addClient(new Client("Peter"));
    }

    @Test
    public void testRemoveClientShouldRemoveClient() {
        bank = new Bank("DSK", 100);
        bank.addClient(new Client("Tom"));
        bank.addClient(new Client("Peter"));
        Assert.assertEquals(2, bank.getCount());

        bank.removeClient("Tom");
        Assert.assertEquals(1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveClientShouldThrowExceptionOnMissingClient() {
        bank = new Bank("DSK", 100);
        bank.addClient(new Client("Tom"));
        bank.removeClient("Peter");
    }

    @Test
    public void testLoanWithdrawalShouldReturnClientWithGivenName() {
        bank = new Bank("DSK", 100);
        Client client = new Client("Tom");
        bank.addClient(client);
        Assert.assertTrue(client.isApprovedForLoan());
        Client clientByName = bank.loanWithdrawal("Tom");
        Assert.assertEquals(clientByName, client);
    }

    @Test
    public void testLoanWithdrawalShouldSetTheGivenClientApprovedForLoanStatusToFalse() {
        bank = new Bank("DSK", 100);
        Client client = new Client("Tom");
        bank.addClient(client);
        Assert.assertTrue(client.isApprovedForLoan());
        bank.loanWithdrawal("Tom");
        Assert.assertFalse(client.isApprovedForLoan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawalShouldThrowExceptionOnMissingClient() {
        bank = new Bank("DSK", 100);
        bank.loanWithdrawal("Peter");
    }

    @Test
    public void testStatisticsShouldReturnTextWithAllClientsInTheBank() {
        bank = new Bank("DSK", 100);
        Client client1 = new Client("Tom");
        Client client2 = new Client("John");
        Client client3 = new Client("Antony");
        bank.addClient(client1);
        bank.addClient(client2);
        bank.addClient(client3);

        String actualStatistics = bank.statistics();
        String expectedStatistics = "The client Tom, John, Antony is at the DSK bank!";

        Assert.assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    public void testStatisticsShouldReturnTextWithEmptyClientsIfThereAreNoClients() {
        bank = new Bank("DSK", 100);

        String actualStatistics = bank.statistics();
        String expectedStatistics = "The client  is at the DSK bank!";

        Assert.assertEquals(expectedStatistics, actualStatistics);
    }

}
