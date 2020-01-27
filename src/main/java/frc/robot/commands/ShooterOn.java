package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.ShooterMode;
// import edu.wpi.first.wpilibj.controller.PIDController;

public class ShooterOn extends CommandBase {
  private ShooterMode wantedMode; // global wanted mode

  private Shooter shooter; // global local subsystem

  private boolean finished = false;

  public ShooterOn(Shooter subsystem, ShooterMode mode) {
    addRequirements(subsystem);
    this.shooter = subsystem;
    this.wantedMode = mode;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.shooter.setShooterMode(this.wantedMode);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (this.shooter.getShooterMode() == this.wantedMode) {
      this.finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }

}
