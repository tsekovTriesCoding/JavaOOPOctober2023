package A07ReflectionAndAnnotations.Lab.Annotations.P05CodingTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {

    @Author(name = "George")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);

    }


    @Author(name = "Peter")
    public static void printMethodsByAuthor(Class<?> clazz) {
        Map<String, List<String>> methodsByAuthor = new HashMap<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Author author = declaredMethod.getAnnotation(Author.class);
            if (author != null) {
                methodsByAuthor.putIfAbsent(author.name(), new ArrayList<>());
                methodsByAuthor.get(author.name()).add(declaredMethod.getName() + "()");
            }
        }

        if (!methodsByAuthor.isEmpty()) {
            for (Map.Entry<String, List<String>> stringListEntry : methodsByAuthor.entrySet()) {
                System.out.println(stringListEntry.getKey() + ": " + String.join(", ", stringListEntry.getValue()));
            }
        }
    }

}
