package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces;

import java.util.Collection;

public interface Commando {

    void addMission(Mission mission);
    Collection<Mission> getMissions();

}
