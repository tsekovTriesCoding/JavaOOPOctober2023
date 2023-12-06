package football.entities.player;

public class Men extends BasePlayer {
    public static final double KILOGRAMS = 85.50;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, KILOGRAMS, strength);
    }

    @Override
    public void stimulation() {
        int increasedStrength = super.getStrength() + 145;
        super.setStrength(increasedStrength);
    }
}
