package vehicleShop.core;

import vehicleShop.common.ConstantMessages;
import vehicleShop.common.ExceptionMessages;
import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private VehicleRepository vehicleRepository;
    private WorkerRepository workerRepository;
    private int vehiclesReady;

    public ControllerImpl() {
        this.vehicleRepository = new VehicleRepository();
        this.workerRepository = new WorkerRepository();
        this.vehiclesReady = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;

        switch (type) {
            case "FirstShift":
                worker = new FirstShift(workerName);
                break;
            case "SecondShift":
                worker = new SecondShift(workerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }

        this.workerRepository.add(worker);
        return String.format(ConstantMessages.ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        this.vehicleRepository.add(vehicle);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = this.workerRepository.findByName(workerName);

        if (worker == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Tool tool = new ToolImpl(power);
        worker.getTools().add(tool);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> suitableWorkers = this.workerRepository.getWorkers()
                .stream()
                .filter(w -> w.getStrength() > 70)
                .collect(Collectors.toList());

        if (suitableWorkers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }

        Vehicle vehicle = this.vehicleRepository.findByName(vehicleName);
        Shop shop = new ShopImpl();

        long brokenTools = 0;
        while (!suitableWorkers.isEmpty() && !vehicle.reached()) {
            Worker currentWorker = suitableWorkers.get(0);
            shop.make(vehicle, currentWorker);

            brokenTools += currentWorker.getTools()
                    .stream()
                    .filter(Tool::isUnfit)
                    .count();

            currentWorker.getTools().removeIf(Tool::isUnfit);

            if (!currentWorker.canWork()) {
                suitableWorkers.remove(currentWorker);
            }
        }

        String vehicleDone;
        if (vehicle.reached()) {
            vehicleDone = "done";
            this.vehiclesReady++;
        } else {
            vehicleDone = "not done";
        }

        return String.format(ConstantMessages.VEHICLE_DONE +
                ConstantMessages.COUNT_BROKEN_INSTRUMENTS, vehicleName, vehicleDone, brokenTools);
    }

    @Override
    public String statistics() {
        String statistics = String.format("%d vehicles are ready!", this.vehiclesReady) + System.lineSeparator() +
                "Info for workers:" + System.lineSeparator() +
                this.workerRepository.getWorkers()
                        .stream()
                        .map(Worker::toString)
                        .collect(Collectors.joining(System.lineSeparator()));

        return statistics.trim();
    }
}
