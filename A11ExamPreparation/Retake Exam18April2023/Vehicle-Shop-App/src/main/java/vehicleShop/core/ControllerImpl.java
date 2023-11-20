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
    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;
    private int vehiclesMade;

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.vehiclesMade = 0;
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
        worker.addTool(tool);

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
            Worker worker = suitableWorkers.get(0);
            shop.make(vehicle, worker);
            brokenTools += worker.getTools().stream().filter(Tool::isUnfit).count();

            if (!worker.canWork() || worker.getTools().stream().allMatch(Tool::isUnfit)) {
                suitableWorkers.remove(worker);
                break;
            }
        }

        if (vehicle.reached()) {
            this.vehiclesMade++;
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "done") +
                    String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenTools).trim();
        } else {
            return String.format(ConstantMessages.VEHICLE_DONE, vehicleName, "not done") +
                    String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenTools).trim();
        }
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d vehicles are ready!", this.vehiclesMade)).append(System.lineSeparator());
        sb.append("Info for workers:").append(System.lineSeparator());
        this.workerRepository.getWorkers().forEach(w -> sb.append(w).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
