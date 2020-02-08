package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Deadzone;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive extends CommandBase {
    private DriveTrain dt;
    boolean turning;

    public AutoDrive(DriveTrain dt) {
        addRequirements(dt);
        this.dt = dt;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double steer = 0;
        if (turning) {
            steer = .5;
        }
        dt.drive(.5, steer);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        dt.drive(0, 0, false);
    }

}