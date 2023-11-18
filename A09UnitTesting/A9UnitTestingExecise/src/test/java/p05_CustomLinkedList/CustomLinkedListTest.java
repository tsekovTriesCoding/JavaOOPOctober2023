package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTest {
    private CustomLinkedList<String> list;

    @Before
    public void initializeList() {
        list = new CustomLinkedList<>();
        list.add("Some");
        list.add("Thing");
        list.add("Pesho");
        list.add("Leaf");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetThrowsExceptionWithNegativeIndex() {
        list.get(-12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetThrowsExceptionWithIndexBiggerThanLength() {
        list.get(list.getCount());
    }

    @Test
    public void testGetShouldGetElement() {
        Assert.assertEquals("Invalid element!", "Some", list.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowExceptionWhenIndexIsNegative() {
        list.set(-1, "Wrong");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowExceptionWhenIndexIsBiggerThanLength() {
        list.set(list.getCount(), "Wrong");
    }

    @Test
    public void testSetShouldSetElement() {
        list.set(1, "NewSetElement");
        Assert.assertEquals("Invalid element!", "NewSetElement", list.get(1));
    }

    @Test
    public void testAddShouldAddElement() {
        int countBeforeAdd = list.getCount();
        list.add("Added");
        int countAfterAdd = list.getCount();

        Assert.assertEquals("Incorrect count of elements!", countBeforeAdd + 1, countAfterAdd);
        Assert.assertEquals("Added", list.get(list.getCount() - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtThrowsExceptionWhenIndexIsNegative() {
        list.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtThrowsExceptionWhenIndexIsBiggerThanLength() {
        list.removeAt(list.getCount());
    }

    @Test
    public void testRemoveAtRemovesElement() {
        int countBeforeRemove = list.getCount();
        Assert.assertEquals("Invalid element removed!", "Some", list.removeAt(0));
        int countAfterRemove = list.getCount();
        Assert.assertEquals("Invalid count of elements after remove!", countBeforeRemove - 1, countAfterRemove);
    }

    @Test
    public void testRemoveWithMissingElement() {
        Assert.assertEquals(-1, list.remove("Wrong Element"));
    }

    @Test
    public void testRemoveRemovesElement() {
        int countBeforeRemove = list.getCount();
        Assert.assertEquals("Invalid element removed!", 0, list.remove("Some"));
        int countAfterRemove = list.getCount();
        Assert.assertEquals("Invalid count of elements after remove!", countBeforeRemove - 1, countAfterRemove);
        Assert.assertEquals("Element was not removed correctly!", -1, list.remove("Some"));
    }

    @Test
    public void testIndexOfShouldFindIndex() {
        Assert.assertEquals(0, list.indexOf("Some"));
    }
    @Test
    public void testIndexOfShouldNotFindIndexOfMissingElement() {
        Assert.assertEquals(-1, list.indexOf("Wrong element"));
    }

    @Test
    public void testContainsShouldReturnTrueIfElementExists() {
        Assert.assertTrue(list.contains("Some"));
    }
    @Test
    public void testContainsShouldReturnFalseIfElementIsMissing() {
        Assert.assertFalse(list.contains("Wrong element"));
    }





}
