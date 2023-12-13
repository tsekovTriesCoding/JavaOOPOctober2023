package harpoonDiver.models.diver;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

public abstract class BaseDiver implements Diver {
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    protected BaseDiver(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canDive() {
        return this.oxygen > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return this.seaCatch;
    }

    @Override
    public void shoot() {
        double newOxygen = this.oxygen - 30;

        this.setOxygen(Math.max(newOxygen, 0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(ConstantMessages.FINAL_DIVER_NAME, this.name)).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, this.oxygen)).append(System.lineSeparator());

        String diverCatch;
        if (this.seaCatch.getSeaCreatures().isEmpty()) {
            diverCatch = "None";
        } else {
            diverCatch = String.join(ConstantMessages.FINAL_DIVER_CATCH_DELIMITER, this.seaCatch.getSeaCreatures());
        }

        sb.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, diverCatch));

        return sb.toString().trim();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO);
        }

        this.oxygen = oxygen;
    }
}
