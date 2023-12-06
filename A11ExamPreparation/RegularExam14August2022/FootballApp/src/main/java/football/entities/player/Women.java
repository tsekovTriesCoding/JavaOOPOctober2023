package football.entities.player;

public class Women extends BasePlayer {
    public static final double KILOGRAMS = 60.00;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, KILOGRAMS, strength);
    }

    @Override
    public void stimulation() {
        int increasedStrength = super.getStrength() + 115;
        super.setStrength(increasedStrength);
    }
}
