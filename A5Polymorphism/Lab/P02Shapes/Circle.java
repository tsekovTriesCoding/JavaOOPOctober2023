package A5Polymorphism.Lab.P02Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculatePerimeter() {
        System.out.println(2 * Math.PI * this.radius);
    }

    @Override
    protected void calculateArea() {
        System.out.println(Math.PI * Math.pow(this.radius, 2));
    }
}
