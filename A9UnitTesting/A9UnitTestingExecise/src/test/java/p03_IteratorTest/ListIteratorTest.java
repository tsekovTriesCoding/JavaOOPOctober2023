package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.List;


public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] ELEMENTS = {"Tom", "Cat", "List", "Something"};

    @Before
    public void initializeElementsList() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithNullParam() throws OperationNotSupportedException {
        listIterator = new ListIterator(null);
    }

    @Test
    public void testIfConstructorCreatesObject() {
        List<String> elements = listIterator.getElements();
        Assert.assertEquals("Lists are not the same!", elements, Arrays.asList(ELEMENTS));
        Assert.assertEquals(elements.size(), ELEMENTS.length);
    }

    @Test
    public void testMoveReturnsTrueIfMoveIsSuccessful() {
        Assert.assertTrue(listIterator.move());
    }

    @Test
    public void testMoveReturnsFalseIfThereIsNoNextIndex() {
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }

    @Test
    public void testHasNextShouldReturnTrueWhenThereIsNextIndex() {
        boolean b = listIterator.hasNext();
        Assert.assertTrue(b);
    }
    @Test
    public void testHasNextShouldReturnFalseWhenThereIsNoNextIndex() {
        listIterator.move();
        listIterator.move();
        listIterator.move();
        boolean afterMove = listIterator.hasNext();
        Assert.assertFalse(afterMove);
    }

    @Test(expected = IllegalStateException.class)
    public void printThrowsExceptionIfElementsIsEmpty() throws OperationNotSupportedException {
        listIterator = new ListIterator();
        listIterator.print();
    }

    @Test
    public void printReturnsValidElement() {
        listIterator.print();
    }

}
