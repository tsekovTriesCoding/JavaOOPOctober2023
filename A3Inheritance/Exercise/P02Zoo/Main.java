package A3Inheritance.Exercise.P02Zoo;

public class Main {
    public static void main(String[] args) {
        Bear bear = new Bear("Pesho");
        System.out.println(bear.getName());

        Snake snake = new Snake("repo");
        System.out.println(snake.getName());

        Gorilla gorilla = new Gorilla("Jack");
        System.out.println(gorilla.getName());
    }
}
