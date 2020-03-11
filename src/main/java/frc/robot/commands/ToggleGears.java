package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ToggleGears extends CommandBase {
    private DriveTrain dt;

    public ToggleGears(DriveTrain dt) {
        this.dt = dt;
    }

    @Override
    public void initialize() {
        if (this.dt.getDriveGear() == DriveTrain.DriveGear.HIGH_GEAR) {
            this.dt.setDriveGear(DriveTrain.DriveGear.LOW_GEAR);
        } else {
            this.dt.setDriveGear(DriveTrain.DriveGear.HIGH_GEAR);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
