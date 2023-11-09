package A7ReflectionAndAnnotations.Exercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
        Field[] declaredFields = richSoilLandClass.getDeclaredFields();

        String command = scanner.nextLine();
        Consumer<Field> fieldPrinter = field -> System.out.printf("%s %s %s%n",
                Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());

        while (!command.equals("HARVEST")) {
            switch (command) {
                case "private":
                    Arrays.stream(declaredFields)
                            .filter(f -> Modifier.isPrivate(f.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "protected":
                    Arrays.stream(declaredFields)
                            .filter(f -> Modifier.isProtected(f.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "public":
                    Arrays.stream(declaredFields)
                            .filter(f -> Modifier.isPublic(f.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "all":
                    Arrays.stream(declaredFields)
                            .forEach(fieldPrinter);
                    break;
            }


            command = scanner.nextLine();
        }
    }
}
