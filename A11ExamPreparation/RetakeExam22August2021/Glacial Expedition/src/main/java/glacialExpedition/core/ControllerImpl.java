package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStatesCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.exploredStatesCount = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }

        this.explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(List.of(exhibits));
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);

        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        this.explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = this.stateRepository.byName(stateName);

        List<Explorer> suitableExplorers = this.explorerRepository.getCollection()
                .stream()
                .filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();
        mission.explore(state, suitableExplorers);
        this.exploredStatesCount++;

        long retiredExplorers = suitableExplorers
                .stream()
                .filter(e -> !e.canSearch())
                .count();
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        String result = String.format("%d states were explored.", this.exploredStatesCount) + System.lineSeparator() +
                "Information for the explorers:" + System.lineSeparator() +
                this.explorerRepository.getCollection()
                        .stream()
                        .map(Explorer::toString)
                        .collect(Collectors.joining(System.lineSeparator()));

        return result.trim();
    }
}
