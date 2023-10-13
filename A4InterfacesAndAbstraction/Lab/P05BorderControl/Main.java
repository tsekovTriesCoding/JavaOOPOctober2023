package A4InterfacesAndAbstraction.Lab.P05BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Identifiable> identifiableObjects = new ArrayList<>();
        while (!"End".equals(input)) {
            String[] info = input.split("\\s+");

            Identifiable identifiable;
            if (info.length == 2) {
                //robot
                String model = info[0];
                String id = info[1];
                identifiable = new Robot(id, model);
            } else {
                //citizen
                String name = info[0];
                int age = Integer.parseInt(info[1]);
                String id = info[2];
                identifiable = new Citizen(name, age, id);
            }

            identifiableObjects.add(identifiable);

            input = scanner.nextLine();
        }

        String lastDigitsOfFakeIds = scanner.nextLine();

        identifiableObjects
                .stream()
                .map(Identifiable::getId)
                .filter(id -> id.endsWith(lastDigitsOfFakeIds))
                .forEach(System.out::println);
    }
}
