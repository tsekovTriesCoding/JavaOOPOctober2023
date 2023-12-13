package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        List<Diver> suitableDivers = divers
                .stream()
                .filter(Diver::canDive)
                .collect(Collectors.toList());

        List<String> seaCreaturesOnSite = new ArrayList<>(divingSite.getSeaCreatures());

        while (!suitableDivers.isEmpty() && !seaCreaturesOnSite.isEmpty()) {
            Diver currentDiver = suitableDivers.get(0);
            String currentSeaCreature = seaCreaturesOnSite.get(0);

            currentDiver.shoot();
            currentDiver.getSeaCatch().getSeaCreatures().add(currentSeaCreature);
            seaCreaturesOnSite.remove(currentSeaCreature);
            divingSite.getSeaCreatures().remove(currentSeaCreature);

            if (!currentDiver.canDive()) {
                suitableDivers.remove(currentDiver);
            }
        }
    }
}
