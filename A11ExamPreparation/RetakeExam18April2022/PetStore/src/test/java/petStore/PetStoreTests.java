package petStore;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {
    private PetStore petStore;

    @Test
    public void testGetAnimalsShouldReturnListOfAnimals() {
        this.petStore = new PetStore();
        Assert.assertEquals(0, this.petStore.getAnimals().size());
    }

    @Test
    public void testGEtCountShouldReturnTheCountOfAnimals() {
        this.petStore = new PetStore();
        Assert.assertEquals(0, this.petStore.getCount());
    }

    @Test
    public void testFindAllAnimalsWithMaxKilogramsShouldReturnAnimalsWithMaxKilograms() {
        this.petStore = new PetStore();
        Animal animal = new Animal("Lion", 100, 2000);
        this.petStore.addAnimal(animal);
        this.petStore.addAnimal(new Animal("Monkey", 67, 12000));
        this.petStore.addAnimal(new Animal("Snake", 2, 5678));
        this.petStore.addAnimal(new Animal("Pigeon", 1, 20));

        List<Animal> allAnimalsWithMaxKilograms = this.petStore.findAllAnimalsWithMaxKilograms(67);
        Assert.assertEquals(1, allAnimalsWithMaxKilograms.size());
        Assert.assertEquals(animal, allAnimalsWithMaxKilograms.get(0));
    }

    @Test
    public void testFindAllAnimalsWithMaxKilogramsShouldReturnEmptyList() {
        this.petStore = new PetStore();
        Animal animal = new Animal("Lion", 100, 2000);
        this.petStore.addAnimal(animal);
        this.petStore.addAnimal(new Animal("Monkey", 67, 12000));
        this.petStore.addAnimal(new Animal("Snake", 2, 5678));
        this.petStore.addAnimal(new Animal("Pigeon", 1, 20));

        List<Animal> allAnimalsWithMaxKilograms = this.petStore.findAllAnimalsWithMaxKilograms(100);
        Assert.assertEquals(0, allAnimalsWithMaxKilograms.size());
    }

    @Test
    public void testAddAnimalShouldAddAnimalToPetStore() {
        this.petStore = new PetStore();
        Animal animal = new Animal("Lion", 100, 2000);
        Assert.assertEquals(0, this.petStore.getCount());

        this.petStore.addAnimal(animal);
        Assert.assertEquals(1, this.petStore.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowExceptionOnNullAnimal() {
        this.petStore = new PetStore();
        this.petStore.addAnimal(null);
    }

    @Test
    public void testGetTheMostExpensiveAnimalShouldReturnTheMostExpensiveAnimal() {
        this.petStore = new PetStore();
        Animal animal1 = new Animal("Lion", 100, 2000);
        Animal animal2 = new Animal("Monkey", 67, 12000);
        Animal animal3 = new Animal("Snake", 2, 5678);
        Animal animal4 = new Animal("Pigeon", 1, 20);
        this.petStore.addAnimal(animal1);
        this.petStore.addAnimal(animal2);
        this.petStore.addAnimal(animal3);
        this.petStore.addAnimal(animal4);

        Animal theMostExpensiveAnimal = this.petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(theMostExpensiveAnimal, animal2);
    }

    @Test
    public void testGetTheMostExpensiveAnimalShouldReturnNull() {
        this.petStore = new PetStore();

        Animal theMostExpensiveAnimal = this.petStore.getTheMostExpensiveAnimal();
        Assert.assertNull(theMostExpensiveAnimal);
    }

    @Test
    public void testFindAllAnimalBySpecieShouldReturnListOfTheSameSpecieAnimals() {
        this.petStore = new PetStore();
        Animal animal1 = new Animal("Lion", 100, 2000);
        Animal animal2 = new Animal("Monkey", 67, 12000);
        Animal animal3 = new Animal("Lion", 2, 5678);
        Animal animal4 = new Animal("Pigeon", 1, 20);
        this.petStore.addAnimal(animal1);
        this.petStore.addAnimal(animal2);
        this.petStore.addAnimal(animal3);
        this.petStore.addAnimal(animal4);

        List<Animal> animalsBySpecie = this.petStore.findAllAnimalBySpecie("Lion");
        Assert.assertEquals(2, animalsBySpecie.size());
        Assert.assertEquals(animalsBySpecie.get(0), animal1);
        Assert.assertEquals(animalsBySpecie.get(1), animal3);
    }
    @Test
    public void testFindAllAnimalBySpecieShouldReturnEmptyListOnMissingSpecie() {
        this.petStore = new PetStore();
        Animal animal1 = new Animal("Lion", 100, 2000);
        Animal animal2 = new Animal("Monkey", 67, 12000);
        Animal animal3 = new Animal("Lion", 2, 5678);
        Animal animal4 = new Animal("Pigeon", 1, 20);
        this.petStore.addAnimal(animal1);
        this.petStore.addAnimal(animal2);
        this.petStore.addAnimal(animal3);
        this.petStore.addAnimal(animal4);

        List<Animal> animalsBySpecie = this.petStore.findAllAnimalBySpecie("Dog");
        Assert.assertEquals(0, animalsBySpecie.size());
    }


}

