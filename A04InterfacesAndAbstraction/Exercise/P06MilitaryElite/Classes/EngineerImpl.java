package A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.Corps;
import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Engineer;
import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Repair;

import java.util.Collection;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    Collection<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps, Collection<Repair> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = repairs;
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Repairs:").append(System.lineSeparator());
        this.repairs.forEach(r -> sb.append("  ").append(r));

        return sb.toString();
    }
}
