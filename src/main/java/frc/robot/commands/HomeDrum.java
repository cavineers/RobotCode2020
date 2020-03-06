package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class HomeDrum extends CommandBase {
    private Drum drum;
    private int stage = 0;

    public HomeDrum(Drum drum) {
        this.drum = drum;
        addRequirements(drum);
    }

    @Override
    public void initialize() {
        System.out.println("Homing");
    }

    @Override
    public void execute() {
        this.drum.motor.disable();
        this.drum.motor.set(-0.2);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Homed");
        this.drum.motor.set(0.0);
        this.drum.motor.setSelectedSensorPosition(0);
        this.drum.makeSetpoint(0);
        this.drum.enable();
    }

    @Override
    public boolean isFinished() {
        return this.drum.isLimitPressed();
    }
}
