package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class ControllerImpl implements Controller {
    private Collection<Computer> computers;
    private Collection<Component> components;
    private Collection<Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer = this.computers
                .stream()
                .filter(c -> id == c.getId())
                .findFirst()
                .orElse(null);

        if (computer != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }

        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        this.computers.add(computer);
        return String.format(OutputMessages.ADDED_COMPUTER, computer.getId());
    }

    @Override
    public String addPeripheral(int computerId,
                                int id,
                                String peripheralType,
                                String manufacturer,
                                String model,
                                double price,
                                double overallPerformance,
                                String connectionType) {

        Computer computer = getComputerById(computerId);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        Peripheral peripheral;

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        Peripheral existingPeripheral = this.peripherals
                .stream()
                .filter(p -> peripheral.getId() == p.getId())
                .findFirst()
                .orElse(null);

        if (existingPeripheral != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }

        computer.addPeripheral(peripheral);
        this.peripherals.add(peripheral);

        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computer.getId());
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = getComputerById(computerId);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        computer.removePeripheral(peripheralType);
        Peripheral peripheral = this.peripherals
                .stream()
                .filter(p -> peripheralType.equals(p.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        this.peripherals.remove(peripheral);

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    private Computer getComputerById(int computerId) {
        return this.computers
                .stream()
                .filter(c -> computerId == c.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public String addComponent(int computerId,
                               int id,
                               String componentType,
                               String manufacturer,
                               String model,
                               double price,
                               double overallPerformance,
                               int generation) {

        Computer computer = getComputerById(computerId);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        Component component;

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        Component comp = this.components
                .stream()
                .filter(c -> component.getId() == c.getId())
                .findFirst()
                .orElse(null);

        if (comp != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }

        computer.addComponent(component);
        this.components.add(component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, component.getId(), computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = getComputerById(computerId);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        Component component = this.components
                .stream()
                .filter(c -> componentType.equals(c.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        computer.removeComponent(componentType);
        this.components.remove(component);

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = getComputerById(id);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        this.computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer computer = this.computers
                .stream()
                .filter(c -> budget >= c.getPrice())
                .sorted(Comparator.comparingDouble(Computer::getOverallPerformance).reversed())
                .limit(1)
                .findFirst()
                .orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }

        this.computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = getComputerById(id);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        return computer.toString();
    }
}
