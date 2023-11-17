package A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Classes;

import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Enums.State;
import A04InterfacesAndAbstraction.Exercise.P06MilitaryElite.Interfaces.Mission;

public class MissionImpl implements Mission {
    private String codeName;
    private State state;

    public MissionImpl(String codeName, State state) {
        this.codeName = codeName;
        this.setState(state);
    }

    public void setState(State state) {
        if (state.equals(State.inProgress) || state.equals(State.finished)) {
            this.state = state;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getCodeName() {
        return this.codeName;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void completeMission() {
        this.setState(State.finished);
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s%n",getCodeName(), getState());
    }
}
