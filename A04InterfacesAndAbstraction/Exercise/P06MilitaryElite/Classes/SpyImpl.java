package A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("Code Number: %s", this.codeNumber) +
                System.lineSeparator();
    }
}