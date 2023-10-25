package A5Polymorphism.Lab.P02Shapes;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5D);
        circle.calculateArea();
        circle.calculatePerimeter();

        Shape rectangle = new Rectangle(4D, 7D);
        rectangle.calculateArea();
        rectangle.calculatePerimeter();
    }


}
