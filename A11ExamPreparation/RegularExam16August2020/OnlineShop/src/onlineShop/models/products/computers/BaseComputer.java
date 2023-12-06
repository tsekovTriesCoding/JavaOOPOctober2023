package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        double peripheralsSum = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        double componentsSum = this.components.stream().mapToDouble(Component::getPrice).sum();

        return super.getPrice() + peripheralsSum + componentsSum;
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        }

        double componentsAveragePerformance = this.components.
                stream().
                mapToDouble(Component::getOverallPerformance).
                average().
                orElse(0);

        return componentsAveragePerformance + super.getOverallPerformance();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        Component comp = this.components
                .stream()
                .filter(c -> component.getClass().getSimpleName().equals(c.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        if (comp != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT,
                    component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }

        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = this.components
                .stream()
                .filter(c -> componentType.equals(c.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        if (this.components.isEmpty() || component == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }

        this.components.remove(component);

        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        Peripheral per = this.peripherals
                .stream()
                .filter(p -> peripheral.getClass().getSimpleName().equals(p.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        if (per != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL,
                    per.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }

        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals
                .stream()
                .filter(p -> peripheralType.equals(p.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        if (this.peripherals.isEmpty() || peripheral == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,
                    peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.remove(peripheral);

        return peripheral;
    }

    @Override
    public String toString() {
        double avrPerformancePeripherals = this.peripherals.
                stream().
                mapToDouble(Peripheral::getOverallPerformance).
                average()
                .orElse(0);

        String toString = String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)",
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(),
                this.getManufacturer(), this.getModel(), this.getId());

        StringBuilder sb = new StringBuilder();
        sb.append(toString).append(System.lineSeparator());
        sb.append(String.format(" Components (%d):", this.components.size())).append(System.lineSeparator());
        this.components.forEach(c -> sb.append("  ").append(c).append(System.lineSeparator()));
        sb.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):",
                this.peripherals.size(), avrPerformancePeripherals)).append(System.lineSeparator());
        this.peripherals.forEach(p -> sb.append("  ").append(p).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
