package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntakePistons extends CommandBase {
    private Intake intake;

    public ToggleIntakePistons(Intake intake) {
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void initialize() {
        if (this.intake.getPistonState() == Intake.IntakePistonState.EXTENDED) {
            this.intake.setPistonState(Intake.IntakePistonState.RETRACTED);
        } else {
            this.intake.setPistonState(Intake.IntakePistonState.EXTENDED);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
