package A7ReflectionAndAnnotations.Lab;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class P02GettersAndSetters {
    public static void main(String[] args) {
        Class<Reflection> reflection = Reflection.class;

        Method[] declaredMethods = reflection.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> {
                    if (isAGetterMethod(m)) {
                        System.out.printf("%s will return class %s%n", m.getName(), m.getReturnType().getName());
                    } else if (isASetterMethod(m)) {
                        System.out.printf("%s and will set field of class %s%n", m.getName(), m.getParameterTypes()[0].getName());
                    }
                });
    }

    private static boolean isASetterMethod(Method m) {
        return m.getName().startsWith("set") && m.getReturnType().equals(void.class) && m.getParameterCount() == 1;
    }

    private static boolean isAGetterMethod(Method m) {
        return m.getName().startsWith("get") && m.getParameterCount() == 0 && !m.getReturnType().equals(void.class);
    }
}
