package A2Encapsulation.Exercise.P04PizzaCalories;

import java.util.Arrays;

public class Dough {
    private FlourType flourType;
    private BakingTechnique bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        boolean flourTypeExists = (Arrays.stream(FlourType.values()).anyMatch(e -> e.name().equals(flourType)));

        if (!flourTypeExists) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = FlourType.valueOf(flourType);
    }

    private void setBakingTechnique(String bakingTechnique) {
        boolean bakingTechniqueExists = (Arrays.stream(BakingTechnique.values()).anyMatch(e -> e.name().equals(bakingTechnique)));

        if (!bakingTechniqueExists) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = BakingTechnique.valueOf(bakingTechnique);
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (2 * this.weight) * flourType.getModifier() * bakingTechnique.getModifier();
    }

}
