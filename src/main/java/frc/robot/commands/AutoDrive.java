package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive extends CommandBase {
    private DriveTrain dt;
    boolean turning;
    double steer;

    public AutoDrive(DriveTrain dt, double steer) {
        addRequirements(dt);
        this.dt = dt;
        this.steer = steer;
    }

    @Override
    public void initialize() {
        try {
            dt.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
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