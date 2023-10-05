package A2Encapsulation.Exercise.P03ShoppingSpree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();
        try {
            Arrays.stream(scanner.nextLine().split(";"))
                    .forEach(p -> {
                        String[] tokens = p.split("=");
                        Person person = new Person(tokens[0], Double.parseDouble(tokens[1]));
                        people.putIfAbsent(person.getName(), person);
                    });

            Arrays.stream(scanner.nextLine().split(";"))
                    .forEach(p -> {
                        String[] tokens = p.split("=");
                        Product product = new Product(tokens[0], Double.parseDouble(tokens[1]));
                        products.putIfAbsent(product.getName(), product);
                    });
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] tokens = command.split("\\s+");
            String name = tokens[0];
            Person person = people.get(name);

            String productName = tokens[1];
            Product product = products.get(productName);

            try {
                person.buyProduct(product);
                System.out.printf("%s bought %s%n", name, product.getName());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            command = scanner.nextLine();
        }

        people.values().forEach(person -> {
            if (person.getProducts().isEmpty()) {
                System.out.printf("%s - Nothing bought%n", person.getName());
            } else {
                System.out.print(person.getName() + " - ");
                String productsName = person.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", "));
                System.out.println(productsName);
            }
        });

    }
}
