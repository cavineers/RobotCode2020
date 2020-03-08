package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederOff extends CommandBase {
    private Feeder feeder;

    public FeederOff(Feeder feeder) {
        addRequirements(feeder);
        this.feeder = feeder;
    }

    @Override
    public void initialize() {
        this.feeder.setState(Feeder.FeederState.DISABLED);
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
