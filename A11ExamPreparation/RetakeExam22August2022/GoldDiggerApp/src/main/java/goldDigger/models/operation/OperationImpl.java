package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        List<Discoverer> validDiscoverers = discoverers.stream()
                .filter(d -> d.getEnergy() >= 0)
                .collect(Collectors.toList());

        Collection<String> exhibits = spot.getExhibits();

        for (Discoverer discoverer : validDiscoverers) {
            if (exhibits.isEmpty()) {
                break;
            }
            for (String exhibit : exhibits) {
                discoverer.dig();
                discoverer.getMuseum().getExhibits().add(exhibit);

                if (discoverer.getEnergy() <= 0) {
                    break;
                }
            }
            exhibits.removeAll(discoverer.getMuseum().getExhibits());
        }
    }
}
