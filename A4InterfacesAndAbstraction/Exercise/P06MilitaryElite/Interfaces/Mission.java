package A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces;

import A4InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.State;

public interface Mission {
    String getCodeName();

    State getState();

    void completeMission();
}
