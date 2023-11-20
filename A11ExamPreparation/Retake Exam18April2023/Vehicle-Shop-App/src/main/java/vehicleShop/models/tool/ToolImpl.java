package vehicleShop.models.tool;

import vehicleShop.common.ExceptionMessages;

public class ToolImpl implements Tool {
    private int power;

    public ToolImpl(int power) {
        this.setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
        int newPower = this.getPower() - 5;

        if (newPower < 0) {
            newPower = 0;
        }

        this.setPower(newPower);
    }

    @Override
    public boolean isUnfit() {
        return this.power == 0;
    }
}
