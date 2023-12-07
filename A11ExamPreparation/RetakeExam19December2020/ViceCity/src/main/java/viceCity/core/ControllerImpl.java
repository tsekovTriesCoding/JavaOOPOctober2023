package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private GunRepository gunRepository;
    private Player mainPlayer;
    private Collection<Player> civilPlayers;

    public ControllerImpl() {
        this.gunRepository = new GunRepository();
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new ArrayList<>();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);

        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;

        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }

        this.gunRepository.add(gun);

        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.gunRepository.getModels().isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        Gun gun = this.gunRepository.getModels().iterator().next();

        if (name.equals("Vercetti")) {
            mainPlayer.getGunRepository().add(gun);
            this.gunRepository.remove(gun);

            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        }

        Player player = this.civilPlayers
                .stream()
                .filter(p -> name.equals(p.getName()))
                .findFirst()
                .orElse(null);

        if (player == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        player.getGunRepository().add(gun);
        this.gunRepository.remove(gun);

        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        Neighbourhood neighbourhood = new GangNeighbourhood();

        long aliveCiviliansBeforeFight = this.civilPlayers.stream().filter(Player::isAlive).count();
        neighbourhood.action(this.mainPlayer, this.civilPlayers);
        long aliveCivilPlayersAfterFight = this.civilPlayers.stream().filter(Player::isAlive).count();

        if (this.mainPlayer.getLifePoints() == 100 &&
                this.civilPlayers.stream().allMatch(p -> p.getLifePoints() == 50) &&
                aliveCiviliansBeforeFight == aliveCivilPlayersAfterFight) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }

        long deadCivilians = this.civilPlayers.stream()
                .filter(p -> !p.isAlive())
                .count();

        this.civilPlayers.removeIf(p -> !p.isAlive());

        String sb = "A fight happened:" + System.lineSeparator() +
                String.format("Tommy live points: %d!", this.mainPlayer.getLifePoints()) + System.lineSeparator() +
                String.format("Tommy has killed: %d players!", deadCivilians) + System.lineSeparator() +
                String.format("Left Civil Players: %d!", this.civilPlayers.size());
        return sb.trim();
    }
}
