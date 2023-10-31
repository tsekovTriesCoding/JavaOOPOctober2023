package A7ReflectionAndAnnotations.Lab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class P03HighQualityMistakes {
    public static void main(String[] args) {

        Class<Reflection> reflection = Reflection.class;

        Field[] declaredFields = reflection.getDeclaredFields();
        Arrays.stream(declaredFields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.printf("%s must be private!%n", f.getName()));

        Method[] declaredMethods = reflection.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .filter(P03HighQualityMistakes::isAGetterMethod)
                .filter(m -> !Modifier.isPublic(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s have to be public!%n", m.getName()));

        Arrays.stream(declaredMethods)
                .filter(P03HighQualityMistakes::isASetterMethod)
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s have to be private!%n", m.getName()));

    }

    private static boolean isASetterMethod(Method m) {
        return m.getName().startsWith("set") && m.getReturnType().equals(void.class) && m.getParameterCount() == 1;
    }

    private static boolean isAGetterMethod(Method m) {
        return m.getName().startsWith("get") && m.getParameterCount() == 0 && !m.getReturnType().equals(void.class);
    }

}
