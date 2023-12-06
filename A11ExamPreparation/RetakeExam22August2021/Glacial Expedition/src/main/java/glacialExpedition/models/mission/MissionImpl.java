package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<Explorer> readyExplorers = explorers
                .stream()
                .filter(e -> e.getEnergy() >= 0)
                .collect(Collectors.toList());

        List<String> stateExhibits = new ArrayList<>(state.getExhibits());

        for (Explorer readyExplorer : readyExplorers) {
            while (readyExplorer.canSearch() && !stateExhibits.isEmpty()) {
                String currentExhibit = stateExhibits.get(0);
                readyExplorer.search();
                readyExplorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
            }

        }
    }
}
