package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {
    private BankVault bankVault;

    @Before
    public void init() {
        this.bankVault = new BankVault();
    }

    @Test
    public void testGetVaultCellsShouldReturnCorrectVaultCells() {
        Map<String, Item> testVault = new LinkedHashMap<>();
        testVault.put("A1", null);
        testVault.put("A2", null);
        testVault.put("A3", null);
        testVault.put("A4", null);
        testVault.put("B1", null);
        testVault.put("B2", null);
        testVault.put("B3", null);
        testVault.put("B4", null);
        testVault.put("C1", null);
        testVault.put("C2", null);
        testVault.put("C3", null);
        testVault.put("C4", null);

        Assert.assertEquals(testVault, this.bankVault.getVaultCells());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemShouldThrowExceptionOnMissingCell() throws OperationNotSupportedException {
        this.bankVault.addItem("M1", new Item("Peter", "fkcui7"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemShouldThrowExceptionOnTakenCell() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", new Item("Peter", "fkcui7"));
        this.bankVault.addItem("A1", new Item("John", "asf5"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddItemShouldThrowExceptionOnAddingExistingItem() throws OperationNotSupportedException {
        Item item = new Item("Peter", "fkcui7");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A3", item);
    }

    @Test
    public void testAddItemShouldAddCorrectly() throws OperationNotSupportedException {
        Assert.assertNull(this.bankVault.getVaultCells().get("A1"));
        Item item = new Item("Peter", "fkcui7");

        String addedItem = this.bankVault.addItem("A1", item);
        Item expectedItem = this.bankVault.getVaultCells().get("A1");
        Assert.assertEquals(expectedItem, item);
        Assert.assertEquals("Item:fkcui7 saved successfully!", addedItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemShouldThrowExceptionOnMissingCell() {
        this.bankVault.removeItem("M1", new Item("Peter", "fkcui7"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemShouldThrowExceptionOnMissingItem() {
        this.bankVault.removeItem("A1", new Item("Peter", "fkcui7"));
    }

    @Test
    public void testRemoveItemShouldRemoveExistingItem() throws OperationNotSupportedException {
        Item item = new Item("Peter", "fkcui7");
        this.bankVault.addItem("A1", item);
        Assert.assertNotNull(this.bankVault.getVaultCells().get("A1"));

        String removedItem = this.bankVault.removeItem("A1", item);
        Assert.assertNull(this.bankVault.getVaultCells().get("A1"));
        Assert.assertEquals("Remove item:fkcui7 successfully!", removedItem);

    }

}