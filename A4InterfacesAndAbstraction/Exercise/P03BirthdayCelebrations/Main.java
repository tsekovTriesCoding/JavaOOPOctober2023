package A4InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Birthable> birthables = new ArrayList<>();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String object = tokens[0];

            switch (object) {
                case "Citizen":
                    String name = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthdate = tokens[4];

                    Birthable citizen = new Citizen(name, age, id, birthdate);
                    birthables.add(citizen);
                    break;
                case "Pet":
                    String petName = tokens[1];
                    String petBirthdate = tokens[2];

                    Birthable pet = new Pet(petName, petBirthdate);
                    birthables.add(pet);
                    break;
            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();

        birthables.stream()
                .filter(b -> b.getBirthDate().endsWith(year))
                .forEach(b -> System.out.println(b.getBirthDate()));
    }

}
