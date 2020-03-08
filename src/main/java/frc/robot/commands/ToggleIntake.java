package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeState;

public class ToggleIntake extends CommandBase {
    private Intake intake;

    public ToggleIntake(Intake intake) {
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void initialize() {
        System.out.println("Intake Toggle");
        if (this.intake.getState() == Intake.IntakeState.OFF) {
            System.out.println("On");
            this.intake.setState(IntakeState.ON);
        } else {
            this.intake.setState(IntakeState.OFF);
            System.out.println("Off");
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
