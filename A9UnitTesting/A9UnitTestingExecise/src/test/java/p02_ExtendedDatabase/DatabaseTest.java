package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Person[] people =
            {new Person(1, "Tony"), new Person(2, "John"), new Person(3, "Gregory")};

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        database = new Database(people);
    }

    //1. конструкор
    //1.1. дали създава правилен обект:
    @Test
    public void testIfConstructorCreatesObject() throws OperationNotSupportedException {
        Person[] databaseElements = database.getElements();
        //дали елементите са еднакви:
        Assert.assertArrayEquals("Arrays are not the same!", people, databaseElements);
        //дали масивите имат един у същ брой елементи:
        Assert.assertEquals("Count of elements is not the same!", people.length, databaseElements.length);
        //дали индексът на двата масива са едни и същи:
        Assert.assertEquals("Index is incorrect!", people.length - 1, databaseElements.length - 1);
    }

    //1.2 създаване на обект с повече от 16 символа:
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Person[] people = new Person[20];
        Database database = new Database(people);
    }

    //1.3 създаване на обект с пo-малко от 1 символ:
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionWithLessThanOneElement() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        Database database = new Database(people);
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
        Person[] elementsBeforeAdd = database.getElements();

        database.add(new Person(4, "Harry"));
        Person[] elementsAfterAdd = database.getElements();
        Assert.assertEquals("Invalid operation!", elementsBeforeAdd.length + 1, elementsAfterAdd.length);

        Person lastPerson = elementsAfterAdd[elementsAfterAdd.length - 1];
        Assert.assertEquals(lastPerson.getId(), 4);
        Assert.assertEquals(lastPerson.getUsername(), "Harry");
    }

    //3. remove метод:
    //3.1. успешно премахване на елемент:
    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Person[] elementsBeforeRemove = database.getElements();
        database.remove();
        Person[] elementsAfterRemove = database.getElements();
        Assert.assertEquals("Invalid remove operation!", elementsBeforeRemove.length - 1, elementsAfterRemove.length);

        Person lastPerson = elementsAfterRemove[elementsAfterRemove.length - 1];
        Assert.assertEquals(lastPerson.getUsername(), "John");
        Assert.assertEquals(lastPerson.getId(), 2);
    }

    //3.2. неуспешно премахване, поради ArraysIndexOutOfBoundsException:
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsExceptionIfInvalidIndex() throws OperationNotSupportedException {
        for (int i = 0; i < people.length; i++) {
            database.remove();
        }
        //вече нямаме елемeнти:
        database.remove();
    }

    //4. findByUsername метод:
    //4.1. username e null:
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsExceptionNullParam() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    //4.2. подаваме валидно име и връщаме този човек:
    @Test
    public void findByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("John");
        Assert.assertEquals("Invalid id of given person!", person.getId(), 2);
        Assert.assertEquals("Invalid username of given person!", person.getUsername(), "John");
    }

    //4.1. имаме повече от 1 човек с това име:
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsExceptionForMoreThanOnePersonWithGivenName() throws OperationNotSupportedException {
        database.add(new Person(4, "Gregory"));
        database.findByUsername("Gregory");
    }

    //4.1. нямаме нито 1 човек с това име:
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsExceptionForNoPersonWithGivenName() throws OperationNotSupportedException {
        database.findByUsername("Pesho");
    }

    //5. findById метод:
    //5.1. подаваме валидно id и връщаме този човек:
    @Test
    public void findById() throws OperationNotSupportedException {
        Person person = database.findById(2);
        Assert.assertEquals("Invalid id of given person!", person.getId(), 2);
        Assert.assertEquals("Invalid username of given person!", person.getUsername(), "John");
    }

    //5.2. имаме повече от 1 човек с това id:
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsExceptionForMoreThanOnePersonWithGivenId() throws OperationNotSupportedException {
        database.add(new Person(3, "Samantha"));
        database.findById(3);
    }

    //5.3. нямаме нито 1 човек с това id:
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsExceptionForNoPersonWithGivenId() throws OperationNotSupportedException {
        database.findById(20);
    }


}
