package A5Polymorphism.Lab.P04WildFarm;

public abstract class Animal {
    String animalName;
    String animalType;
    Double animalWeight;
    Integer foodEaten;

    protected Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    protected void setFoodEaten(Integer foodEaten) {
        this.foodEaten += foodEaten;
    }

    public abstract void makeSound();

    public abstract void eat(Food food);

    protected String getAnimalName() {
        return animalName;
    }

    protected String getAnimalType() {
        return animalType;
    }

    protected Double getAnimalWeight() {
        return animalWeight;
    }

    protected int getFoodEaten() {
        return foodEaten;
    }
}
