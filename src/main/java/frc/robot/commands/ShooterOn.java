package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterOn extends CommandBase {
  private Shooter shooter;

  public ShooterOn(Shooter shooter) {
    addRequirements(shooter);
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    this.shooter.enable();
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