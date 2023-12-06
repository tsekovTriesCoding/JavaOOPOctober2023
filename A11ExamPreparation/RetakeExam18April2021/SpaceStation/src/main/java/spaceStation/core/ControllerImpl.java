package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int planetsExplored;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.planetsExplored = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;

        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }

        this.astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        this.planetRepository.add(planet);

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);

        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        this.astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = this.planetRepository.findByName(planetName);

        List<Astronaut> suitableAstronauts = this.astronautRepository.getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if (suitableAstronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();
        mission.explore(planet, suitableAstronauts);
        long deadAstronauts = suitableAstronauts.stream().filter(a -> !a.canBreath()).count();
        this.planetsExplored++;
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {

        String report = String.format("%d planets were explored!", this.planetsExplored) + System.lineSeparator() +
                "Astronauts info:" + System.lineSeparator() +
                this.astronautRepository.getModels()
                        .stream()
                        .map(Astronaut::toString)
                        .collect(Collectors.joining(System.lineSeparator()));

        return report.trim();
    }
}
