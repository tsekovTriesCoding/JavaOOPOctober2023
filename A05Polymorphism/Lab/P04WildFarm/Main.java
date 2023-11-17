package A05Polymorphism.Lab.P04WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();
        while (!"End".equals(input)) {
            String[] animalTokens = input.split("\\s+");
            String[] foodTokens = scanner.nextLine().split("\\s+");
            Animal animal = createAnimal(animalTokens);
            animal.makeSound();

            try {
                Food food = createFood(foodTokens);
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);
            input = scanner.nextLine();
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static Food createFood(String[] tokens) {
        Food food = null;
        String foodType = tokens[0];
        int quantity = Integer.parseInt(tokens[1]);

        switch (foodType) {
            case "Vegetable":
                food = new Vegetable(quantity);
                break;
            case "Meat":
                food = new Meat(quantity);
                break;
        }

        return food;
    }

    private static Animal createAnimal(String[] tokens) {
        String animalType = tokens[0];
        String animalName = tokens[1];
        Double animalWeight = Double.parseDouble(tokens[2]);
        String animalLivingRegion = tokens[3];
        Animal animal = null;
        switch (animalType) {
            case "Mouse":
                animal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Zebra":
                animal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Tiger":
                animal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Cat":
                String breed = tokens[4];
                animal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, breed);
                break;
        }
        return animal;
    }
}
