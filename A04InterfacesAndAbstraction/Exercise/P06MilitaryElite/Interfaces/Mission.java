package A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces;

import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.State;

public interface Mission {
    String getCodeName();

    State getState();

    void completeMission();
}
