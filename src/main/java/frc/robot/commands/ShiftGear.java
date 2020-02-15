package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ShiftGear extends InstantCommand {
    private DriveTrain dt;
    private DriveTrain.DriveGear gear;
    
    public ShiftGear(DriveTrain dt, DriveTrain.DriveGear gear) {
        // Add requirements
        addRequirements(dt);
        this.dt = dt;
        this.gear = gear;
    }

    @Override
    public void initialize() {
        // Shift to the desired gear
        this.dt.setDriveGear(this.gear);
    }
}
