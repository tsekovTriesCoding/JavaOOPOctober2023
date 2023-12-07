package handball.entities.team;

public class Bulgaria extends BaseTeam {
    public Bulgaria(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        int newAdvantage = super.getAdvantage() + 115;
        super.setAdvantage(newAdvantage);
    }
}
