package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeState;

public class IntakeOff extends CommandBase {
    private Intake in;

    public IntakeOff(Intake in) {
        addRequirements(in);
        this.in = in;
    }

    @Override
    public void initialize() {
        this.in.setState(IntakeState.OFF);
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
