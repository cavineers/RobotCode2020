package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class SpinDrum extends CommandBase {
    private Drum drum;
    
    public SpinDrum(Drum drum) {
        addRequirements(drum);
        this.drum = drum;
    }

    @Override
    public void initialize() {
        this.drum.drumMotor.set(0.5);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
