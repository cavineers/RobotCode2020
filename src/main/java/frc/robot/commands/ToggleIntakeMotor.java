package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntakeMotor extends CommandBase {
    private Intake intake;

    public ToggleIntakeMotor(Intake intake) {
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void initialize() {
        System.out.println("Intake Toggle");
        if (this.intake.getMotorState() == Intake.IntakeMotorState.OFF) {
            this.intake.setMotorState(Intake.IntakeMotorState.ON);
        } else {
            this.intake.setMotorState(Intake.IntakeMotorState.OFF);
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
