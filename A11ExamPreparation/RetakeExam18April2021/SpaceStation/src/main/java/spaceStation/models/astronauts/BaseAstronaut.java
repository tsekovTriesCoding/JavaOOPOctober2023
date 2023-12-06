package spaceStation.models.astronauts;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import java.util.stream.Collectors;

public abstract class BaseAstronaut implements Astronaut {
    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
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
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        this.setOxygen(Math.max(this.getOxygen() - 10, 0));
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    protected void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }

        this.oxygen = oxygen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s", this.name)).append(System.lineSeparator());
        sb.append(String.format("Oxygen: %.0f", this.oxygen)).append(System.lineSeparator());
        sb.append("Bag items: ");
        if (this.bag.getItems().isEmpty()) {
            sb.append("none");
        } else {
            sb.append(String.join(", ", this.bag.getItems()));
        }

        return sb.toString().trim();
    }
}
