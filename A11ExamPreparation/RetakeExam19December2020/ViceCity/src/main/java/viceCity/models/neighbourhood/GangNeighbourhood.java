package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        List<Gun> guns = new ArrayList<>(mainPlayer.getGunRepository().getModels());
        List<Player> civilians = new ArrayList<>(civilPlayers);

        while (!civilians.isEmpty() && !guns.isEmpty()) {
            Player currentCivilian = civilians.get(0);
            Gun currentGun = guns.get(0);

            while (currentCivilian.isAlive() && currentGun.canFire()) {
                currentCivilian.takeLifePoints(currentGun.fire());
            }

            if (!currentCivilian.isAlive()) {
                civilians.remove(currentCivilian);
            }

            if (!currentGun.canFire()) {
                guns.remove(currentGun);
            }
        }

        civilians.removeIf(p -> !p.isAlive());

        while (!civilians.isEmpty() && mainPlayer.isAlive() && civilians.stream().anyMatch(p -> !p.getGunRepository().getModels().isEmpty())) {
            Player currentCivilian = civilians.get(0);
            List<Gun> civilPlayerGuns = new ArrayList<>(currentCivilian.getGunRepository().getModels());

            while (!civilPlayerGuns.isEmpty() && mainPlayer.isAlive() && !civilPlayerGuns.isEmpty()) {
                Gun gun = civilPlayerGuns.get(0);

                mainPlayer.takeLifePoints(gun.fire());

                if (!gun.canFire()) {
                    civilPlayerGuns.remove(0);
                }

            }

        }
    }
}
