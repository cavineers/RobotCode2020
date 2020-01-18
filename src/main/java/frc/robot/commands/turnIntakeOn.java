
package frc.robot.commands;

import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class turnIntakeOn extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    // this toggles the intake motor

    public turnIntakeOn() {

        addRequirements(Robot.intake);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // set the number stopper to 0 so the intake can be toggled
        Robot.intake.numberStopper = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // toggle Intake by using while it will stop after toggling once
        while (Robot.intake.numberStopper == 0) {
            Robot.intake.isOn = !Robot.intake.isOn;
            Robot.intake.numberStopper = 1;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        return false;

    }

}
