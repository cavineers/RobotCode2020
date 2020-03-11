package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ToggleCoastBrakeMode extends CommandBase {
    private DriveTrain dt;
    
    public ToggleCoastBrakeMode(DriveTrain dt) {
        this.dt = dt;
    }

    @Override
    public void initialize() {
        if (this.dt.getCoastBrakeMode() == DriveTrain.DriveCoastBrake.COAST) {
            this.dt.setCoastBrakeMode(DriveTrain.DriveCoastBrake.BRAKE);
        } else {
            this.dt.setCoastBrakeMode(DriveTrain.DriveCoastBrake.COAST);
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
