package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }

        this.services.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplements.addSupplement(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);
        Service service = getServiceByName(serviceName);

        if (supplement == null || service == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        service.addSupplement(supplement);
        this.supplements.removeSupplement(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_ROBOT_TYPE));
        }

        Service service = getServiceByName(serviceName);

        try {
            service.addRobot(robot);
        } catch (IllegalArgumentException ignored) {
            return ConstantMessages.UNSUITABLE_SERVICE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    private Service getServiceByName(String serviceName) {
        return this.services
                .stream()
                .filter(s -> s.getName().equals(serviceName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = getServiceByName(serviceName);

        service.feeding();
        return String.format(ConstantMessages.FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = getServiceByName(serviceName);
        double sumOfAllRobots = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
        double sumOfAllSupplements = service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();

        double totalSum = sumOfAllRobots + sumOfAllSupplements;

        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, totalSum);
    }

    @Override
    public String getStatistics() {
        return this.services
                .stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
