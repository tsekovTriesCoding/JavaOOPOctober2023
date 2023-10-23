package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.Corps;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Commando;
import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Mission;

import java.util.Collection;
import java.util.Collections;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    Collection<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps, Collection<Mission> missions) {
        super(id, firstName, lastName, salary, corps);
        this.missions = missions;
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return Collections.unmodifiableCollection(this.missions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Missions:").append(System.lineSeparator());
        this.missions.forEach(m -> sb.append("  ").append(m));

        return sb.toString();
    }
}
