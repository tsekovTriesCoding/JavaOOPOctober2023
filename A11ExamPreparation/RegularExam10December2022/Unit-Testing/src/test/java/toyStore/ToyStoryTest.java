package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyStoryTest {
    private ToyStore toyStore;

    @Before
    public void init() {
        this.toyStore = new ToyStore();
    }

    @Test
    public void testInit() {
        Map<String, Toy> toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        Assert.assertEquals(toyShelf, this.toyStore.getToyShelf());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowExceptionWhenAddingToyToMissingShelf() throws OperationNotSupportedException {
        this.toyStore.addToy("No", new Toy("Komsed", "236g"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowExceptionWhenAddingToyToTakenShelf() throws OperationNotSupportedException {
        this.toyStore.addToy("A", new Toy("Komsed", "236g"));
        this.toyStore.addToy("A", new Toy("Komsed", "236g"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyShouldThrowExceptionWhenAddingToyThatExists() throws OperationNotSupportedException {
        Toy toy = new Toy("Komsed", "236g");
        this.toyStore.addToy("A", toy);
        this.toyStore.addToy("B", toy);
    }

    @Test
    public void testAddToyShouldAddToyToStore() throws OperationNotSupportedException {
        Assert.assertNull(this.toyStore.getToyShelf().get("A"));
        Toy toy = new Toy("Komsed", "236g");
        this.toyStore.addToy("A", toy);
        Assert.assertEquals(toy, this.toyStore.getToyShelf().get("A"));
    }

    @Test
    public void testAddToyShouldReturnTextWhenAddedToyCorrectly() throws OperationNotSupportedException {
        Assert.assertNull(this.toyStore.getToyShelf().get("A"));
        Toy toy = new Toy("Komsed", "236g");
        String expectedResult = "Toy:236g placed successfully!";
        String actualResult = this.toyStore.addToy("A", toy);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowExceptionOnMissingShelf() {
        this.toyStore.removeToy("No", new Toy("Komsed", "236g"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowExceptionWhenToyToRemoveDoesNotExistInGivenShelf() throws OperationNotSupportedException {
        Toy toy = new Toy("Komsed", "236g");
        this.toyStore.addToy("A", toy);
        Toy toyToRemove = new Toy("Stars", "vjkshus");
        this.toyStore.removeToy("A", toyToRemove);
    }

    @Test
    public void testRemoveToyShouldRemoveToyFromStore() throws OperationNotSupportedException {
        Toy toy = new Toy("Komsed", "236g");
        this.toyStore.addToy("A", toy);
        Assert.assertNotNull(this.toyStore.getToyShelf().get("A"));

        this.toyStore.removeToy("A", toy);
        Assert.assertNull(this.toyStore.getToyShelf().get("A"));
    }

    @Test
    public void testRemoveToyShouldReturnTextWithRemovedToy() throws OperationNotSupportedException {
        Toy toy = new Toy("Komsed", "236g");
        this.toyStore.addToy("A", toy);
        String expectedResult = "Remove toy:236g successfully!";
        String actualResult = this.toyStore.removeToy("A", toy);
        Assert.assertEquals(expectedResult, actualResult);
    }


}