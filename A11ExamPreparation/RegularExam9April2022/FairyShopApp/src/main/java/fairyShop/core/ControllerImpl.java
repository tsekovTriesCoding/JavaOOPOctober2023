package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.helpers.Happy;
import fairyShop.models.helpers.Helper;
import fairyShop.models.helpers.Sleepy;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.instruments.InstrumentImpl;
import fairyShop.models.presents.Present;
import fairyShop.models.presents.PresentImpl;
import fairyShop.models.shops.Shop;
import fairyShop.models.shops.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;
    private int presentsCrafted;
    private int brokenInstruments;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.presentsCrafted = 0;
        this.brokenInstruments = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;

        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }

        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);

        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpers = this.helperRepository.getModels()
                .stream()
                .filter(h -> h.getEnergy() > 50)
                .collect(Collectors.toList());

        if (helpers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        Present present = this.presentRepository.findByName(presentName);
        Shop shop = new ShopImpl();

        while (!helpers.isEmpty() && !present.isDone()) {
            Helper helper = helpers.get(0);
            shop.craft(present, helper);

            if (!helper.canWork() || helper.getInstruments().stream().allMatch(Instrument::isBroken)) {
                helpers.remove(helper);
            }

            if (helper.getInstruments().stream().anyMatch(Instrument::isBroken)) {
                List<Instrument> broken = helper.getInstruments()
                        .stream()
                        .filter(Instrument::isBroken)
                        .collect(Collectors.toList());

                this.brokenInstruments += broken.size();
                helper.getInstruments().removeAll(broken);
            }

        }

        String isDone = "not done";
        if (present.isDone()) {
            this.presentsCrafted++;
            isDone = "done";
        }

        return String.format(ConstantMessages.PRESENT_DONE, presentName, isDone) +
                String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, this.brokenInstruments);

    }

    @Override
    public String report() {

        String result = String.format("%d presents are done!", this.presentsCrafted) + System.lineSeparator() +
                "Helpers info:" + System.lineSeparator() +
                this.helperRepository.getModels()
                        .stream()
                        .map(Helper::toString)
                        .collect(Collectors.joining(System.lineSeparator()));

        return result.trim();
    }
}
