package A5Polymorphism.Exercise.P04Calculator;

public class Extensions {

    public static InputInterpreter buildInterpreter(CalculationEngine engine) {
        return new InputInterpreter(engine);
    }
}
