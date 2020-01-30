
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

/**
 * An example command that uses an example subsystem.
 */
public class DrumInit extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Drum drum;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DrumInit(Drum theDrum) {
    drum = theDrum;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(theDrum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drum.numberStopper = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (drum.numberStopper == 0) {
      drum.drumReady = !drum.drumReady;
      drum.numberStopper = 1;
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
