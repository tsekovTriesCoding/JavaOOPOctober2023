package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {
    public static final int DEFAULT_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void search() {
        double newEnergy = super.getEnergy() - 7;
        super.setEnergy(Math.max(newEnergy, 0));
    }
}
