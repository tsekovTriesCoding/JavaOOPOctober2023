package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.Corps;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.SpecialisedSoldier;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    @Override
    public Corps getCorps() {
        return this.corps;
    }

    public void setCorps(Corps corps) {
        if (corps.equals(Corps.Airforces) || corps.equals(Corps.Marines)) {
            this.corps = corps;
        } else {
            throw  new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Corps: %s",getCorps()) + System.lineSeparator();
    }
}
