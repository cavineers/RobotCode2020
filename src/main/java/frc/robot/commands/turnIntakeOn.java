
package frc.robot.commands;

import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/**
 * An example command that uses an example subsystem.
 */
public class turnIntakeOn extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public turnIntakeOn() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.intake.numberStopper = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    while (Robot.intake.numberStopper == 0) {
      Robot.intake.isOn = !Robot.intake.isOn;
      Robot.intake.numberStopper = 1;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.intake.isOnEnd = !Robot.intake.isOnEnd;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;

  }

}
