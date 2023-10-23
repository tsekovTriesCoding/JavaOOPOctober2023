package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces;

import java.util.Collection;

public interface Engineer {
    public void addRepair(Repair repair);

    public Collection<Repair> getRepairs();
}
