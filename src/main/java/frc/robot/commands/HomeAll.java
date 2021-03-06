package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class HomeAll extends CommandBase {
    private boolean finished = false;
    private RobotContainer rc;

    public HomeAll(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        System.out.println("Started Homing");
        this.rc.hood.disable();
        new HomeHood(this.rc.hood).schedule();
        // new HomeDrum(this.rc.drum).schedule();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Finished Homing");
        this.rc.hood.enable();
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
