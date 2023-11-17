package A05Polymorphism.Exercise.P04Calculator;

public class InputInterpreter {
    Memory memory;
    private CalculationEngine engine;

    public InputInterpreter(CalculationEngine engine){
        this.engine = engine;
        this.memory = new Memory();
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input));
        }catch (Exception ex){
            engine.pushOperation(this.getOperation(input));
        }
        return true;
    }
    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new MultiplicationOperation();
        } else if (operation.equals("/")) {
            return new DivisionOperation();
        } else if (operation.equals("ms")) {
            return new MemorySaveOperation(memory);
        } else if (operation.equals("mr")) {
            return new MemoryRecallOperation(memory);
        }

        return null;
    }
}
