package A05Polymorphism.Lab.P04WildFarm;


public abstract class Feline extends Mammal {

    protected Feline(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        this.setFoodEaten(food.getQuantity());
    }
}
