package A5Polymorphism.Exercise.P04Calculator;

import java.util.ArrayDeque;

public class Memory {
    ArrayDeque<Integer> stack;

    public Memory() {
        this.stack = new ArrayDeque<>();
    }

    public void save(int operand) {
        this.stack.push(operand);
    }

    public int recall() {
        return this.stack.pop();
    }
}
