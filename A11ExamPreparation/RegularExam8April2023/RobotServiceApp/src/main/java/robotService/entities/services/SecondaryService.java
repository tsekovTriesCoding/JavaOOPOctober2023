package robotService.entities.services;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.Robot;

public class SecondaryService extends BaseService{
    public static final int DEFAULT_CAPACITY = 15;
    public SecondaryService(String name) {
        super(name, DEFAULT_CAPACITY);
    }

    @Override
    public void addRobot(Robot robot) {
        if (robot instanceof FemaleRobot) {
            super.addRobot(robot);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
