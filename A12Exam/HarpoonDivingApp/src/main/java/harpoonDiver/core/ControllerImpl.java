package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private int sitesExplored;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.sitesExplored = 0;
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;

        switch (kind) {
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }

        this.diverRepository.add(diver);

        return String.format(ConstantMessages.DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        divingSite.getSeaCreatures().addAll(List.of(seaCreatures));
        this.divingSiteRepository.add(divingSite);

        return String.format(ConstantMessages.DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.byName(diverName);

        if (diver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_DOES_NOT_EXIST, diverName));
        }

        this.diverRepository.remove(diver);

        return String.format(ConstantMessages.DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        List<Diver> suitableDrivers = this.diverRepository.getCollection()
                .stream()
                .filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());

        if (suitableDrivers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }

        DivingSite divingSite = this.divingSiteRepository.byName(siteName);

        Diving diving = new DivingImpl();
        diving.searching(divingSite, suitableDrivers);

        long removedDivers = suitableDrivers.stream().filter(d -> !d.canDive()).count();
        this.sitesExplored++;

        return String.format(ConstantMessages.SITE_DIVING, siteName, removedDivers);
    }

    @Override
    public String getStatistics() {
        String info = String.format(ConstantMessages.FINAL_DIVING_SITES, this.sitesExplored) + System.lineSeparator() +
                String.format(ConstantMessages.FINAL_DIVERS_STATISTICS) + System.lineSeparator() +
                this.diverRepository.getCollection()
                        .stream()
                        .map(Diver::toString)
                        .collect(Collectors.joining(System.lineSeparator()));

        return info.trim();
    }
}
