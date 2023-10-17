package A4InterfacesAndAbstraction.Exercise.P04FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> people = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                //Citizen
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                String birthdate = tokens[3];

                Buyer citizen = new Citizen(name, age, id, birthdate);
                people.put(name, citizen);
            } else {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String group = tokens[2];

                Buyer rebel = new Rebel(name, age, group);
                people.put(name, rebel);
            }

        }

        String name = scanner.nextLine();

        while (!"End".equals(name)) {
            String finalName = name;
            people.forEach((key, value) -> {
                if (key.equals(finalName)) {
                    value.buyFood();
                }
            });

            name = scanner.nextLine();
        }

        int totalFood = people.values()
                .stream()
                .mapToInt(Buyer::getFood)
                .sum();

        System.out.println(totalFood);
    }

}
