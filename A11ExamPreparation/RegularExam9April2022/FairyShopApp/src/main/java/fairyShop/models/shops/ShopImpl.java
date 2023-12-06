package fairyShop.models.shops;

import fairyShop.models.helpers.Helper;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.presents.Present;

import java.util.ArrayList;
import java.util.List;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {
        List<Instrument> instruments = new ArrayList<>(helper.getInstruments());

        while (!present.isDone() && helper.canWork() && instruments.stream().anyMatch(i -> !i.isBroken())) {
            Instrument currentInstrument = instruments.get(0);
            present.getCrafted();
            currentInstrument.use();
            helper.work();

            if (currentInstrument.isBroken()) {
                instruments.remove(currentInstrument);
            }
        }

    }
}
