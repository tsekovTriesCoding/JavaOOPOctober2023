package magicGame.models.region;

import magicGame.common.OutputMessages;
import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {

    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> blackWidowList = magicians
                .stream()
                .filter(m -> m.getClass().getSimpleName().equals("BlackWidow"))
                .collect(Collectors.toList());

        List<Magician> wizardList = magicians
                .stream()
                .filter(m -> m.getClass().getSimpleName().equals("Wizard"))
                .collect(Collectors.toList());

        while (blackWidowList.stream().anyMatch(Magician::isAlive) && wizardList.stream().anyMatch(Magician::isAlive)) {
            for (Magician wizard : wizardList) {
                blackWidowList.forEach(b -> b.takeDamage(wizard.getMagic().fire()));
            }

            for (Magician blackWidow : blackWidowList) {
                wizardList.forEach(w -> w.takeDamage(blackWidow.getMagic().fire()));
            }
        }

        if (blackWidowList.stream().noneMatch(Magician::isAlive)) {
            return String.format(OutputMessages.WIZARDS_WIN);
        }

        return String.format(OutputMessages.BLACK_WIDOWS_WIN);
    }
}
