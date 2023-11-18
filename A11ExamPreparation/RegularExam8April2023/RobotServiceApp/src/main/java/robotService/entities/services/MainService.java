package robotService.entities.services;

import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;

public class MainService extends BaseService{
    public static final int DEFAULT_CAPACITY = 30;
    public MainService(String name) {
        super(name, DEFAULT_CAPACITY);
    }

    @Override
    public void addRobot(Robot robot) {
        if (robot instanceof MaleRobot) {
            super.addRobot(robot);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
