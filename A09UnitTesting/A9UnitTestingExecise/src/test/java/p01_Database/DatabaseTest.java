package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {3, 7, 210, 45, 9, 1};

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    //1. конструкор
    //1.1. дали създава правилен обект:
    @Test
    public void testIfConstructorCreatesObject() throws OperationNotSupportedException {
        Integer[] databaseElements = database.getElements();
        //дали елементите са еднакви:
        Assert.assertArrayEquals("Arrays are not the same!", NUMBERS, databaseElements);
        //дали масивите имат един у същ брой елементи:
        Assert.assertEquals("Count of elements is not the same!", NUMBERS.length, databaseElements.length);
        //дали индексът на двата масива са едни и същи:
        Assert.assertEquals("Index is incorrect!", NUMBERS.length - 1, databaseElements.length - 1);
    }

    //1.2 създаване на обект с повече от 16 символа:
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[20];
        Database database = new Database(numbers);
    }

    //1.3 създаване на обект с пo-малко от 1 символ:
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionWithLessThanOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        Database database = new Database(numbers);
    }

    //2. add метод:
    //2.1. добавяне на null елемент:
    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowExceptionIfNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    //2.2. успешно добавяне на елемент:
    @Test
    public void testAddShouldAddElementCorrectly() throws OperationNotSupportedException {
        Integer[] elementsBeforeAdd = database.getElements();

        database.add(20);
        Integer[] elementsAfterAdd = database.getElements();
        Assert.assertEquals("Invalid operation!", elementsBeforeAdd.length + 1, elementsAfterAdd.length);
        Assert.assertEquals(elementsAfterAdd[elementsAfterAdd.length - 1], Integer.valueOf(20));
    }

    //3. remove метод:
    //3.1. успешно премахване на елемент:
    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Integer[] elementsBeforeRemove = database.getElements();
        database.remove();
        Integer[] elementsAfterRemove = database.getElements();

        Assert.assertEquals("Invalid remove operation!", elementsBeforeRemove.length - 1, elementsAfterRemove.length);
        Assert.assertEquals(elementsAfterRemove[elementsAfterRemove.length - 1], Integer.valueOf(9));
    }

    //3.2. неуспешно премахване, поради ArraysIndexOutOfBoundsException:
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsExceptionIfInvalidIndex() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        //вече нямаме елемeнти:
        database.remove();
    }

}
