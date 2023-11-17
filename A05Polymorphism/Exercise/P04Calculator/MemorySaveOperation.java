package A05Polymorphism.Exercise.P04Calculator;


public class MemorySaveOperation implements Operation {
    private int lastSavedOperand;
    private boolean completed;
    private Memory memory;

    public MemorySaveOperation(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.save(operand);
        this.lastSavedOperand = operand;
        this.completed = true;
    }

    @Override
    public int getResult() {
        return this.lastSavedOperand;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
