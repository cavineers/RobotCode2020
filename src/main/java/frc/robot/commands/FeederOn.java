package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederOn extends CommandBase {
    private Feeder feeder;

    public FeederOn(Feeder feeder) {
        addRequirements(feeder);
        this.feeder = feeder;
    }

    @Override
    public void initialize() {
        this.feeder.setState(Feeder.FeederState.ENABLED);
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
