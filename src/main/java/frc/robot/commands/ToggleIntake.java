package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {
    private Intake intake;

    public ToggleIntake(Intake intake) {
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void initialize() {
        System.out.println("Intake Toggle");
        if (this.intake.getMotorState() == Intake.IntakeMotorState.OFF) {
            this.intake.setMotorState(Intake.IntakeMotorState.ON);
            this.intake.setPistonState(Intake.IntakePistonState.EXTENDED);
        } else {
            this.intake.setMotorState(Intake.IntakeMotorState.OFF);
            this.intake.setPistonState(Intake.IntakePistonState.RETRACTED);
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
