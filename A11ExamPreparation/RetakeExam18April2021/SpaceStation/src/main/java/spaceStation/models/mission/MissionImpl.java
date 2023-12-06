package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<Astronaut> astronautsWithOxygenLeft = astronauts
                .stream()
                .filter(Astronaut::canBreath)
                .collect(Collectors.toList());
        List<String> items = new ArrayList<>(planet.getItems());

        while (!astronautsWithOxygenLeft.isEmpty()) {
            Astronaut currentAstronaut = astronautsWithOxygenLeft.get(0);

            while (!items.isEmpty() && currentAstronaut.canBreath()) {
                String currentItem = items.get(0);
                currentAstronaut.breath();
                currentAstronaut.getBag().getItems().add(currentItem);
                items.remove(currentItem);
            }

            astronautsWithOxygenLeft.remove(0);
        }
    }
}
