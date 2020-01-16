
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

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.intake.getIntakeMotor();
    Robot.intake.isOn = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.intake.isOn = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Robot.intake.isOn=false;
    return false;
  }

}
