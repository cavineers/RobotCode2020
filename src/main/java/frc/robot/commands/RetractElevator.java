package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RetractElevator extends CommandBase {
    private Climber climber;

    public RetractElevator(Climber climber) {
        addRequirements(climber);
        this.climber = climber;
    }

    @Override
    public void initialize() {
        this.climber.setMode(Climber.ClimberMode.RETRACTING);
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
