package A5Polymorphism.Lab.P01MathOperation;

public class MathOperation {
    private int a;
    private int b;
    private int c;
    private int d;

    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return add(a, b) + c;
    }

    public int add(int a, int b, int c, int d) {
        return add(a, b) + add(c, d);
    }
}
