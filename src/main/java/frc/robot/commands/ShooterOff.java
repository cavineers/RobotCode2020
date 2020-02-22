package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterOff extends CommandBase {
  private Shooter shooter;

  public ShooterOff(Shooter shooter) {
    addRequirements(shooter);
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    this.shooter.disable();
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