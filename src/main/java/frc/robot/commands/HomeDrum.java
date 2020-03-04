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
        if (stage == 0) {
            if (this.drum.isLimitPressed()) {
                this.stage++;
                this.drum.motor.set(0);
                this.drum.motor.setSelectedSensorPosition(-6426);
                this.drum.makeSetpoint(0);
                this.drum.enable();
            }
            this.drum.disable();
            this.drum.motor.set(-0.3);
        }
        if (stage == 1) {
            if (this.drum.motor.getSelectedSensorPosition() < 750 && this.drum.motor.getSelectedSensorPosition() > -750) {
                this.stage++;
            } else {
                if (this.drum.motor.getSelectedSensorPosition() < 0) {
                    this.drum.motor.set(0.2);
                } else {
                    this.drum.motor.set(0.2);
                }
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Homed");
        this.drum.motor.set(0.0);
        
    }

    @Override
    public boolean isFinished() {
        return this.stage == 2;
    }
}
