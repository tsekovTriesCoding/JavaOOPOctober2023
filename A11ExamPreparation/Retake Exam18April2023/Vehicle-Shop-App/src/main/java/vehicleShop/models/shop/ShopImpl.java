package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.List;

public class ShopImpl implements Shop {
    @Override
    public void make(Vehicle vehicle, Worker worker) {
        if (worker.canWork() && worker.getTools().stream().anyMatch(t -> !t.isUnfit())) {
            List<Tool> tools = new ArrayList<>(worker.getTools());

            while (!vehicle.reached() && worker.canWork() && !tools.isEmpty()) {
                Tool currentTool = tools.get(0);
                vehicle.making();
                currentTool.decreasesPower();
                worker.working();

                if (currentTool.isUnfit()) {
                    tools.remove(currentTool);
                }
            }
        }
    }
}
