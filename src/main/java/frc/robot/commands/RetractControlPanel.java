package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class RetractControlPanel extends CommandBase {
  private ControlPanel cp;

  public RetractControlPanel(ControlPanel cp) {
    addRequirements(cp);
    this.cp = cp;
  }

  @Override
  public void initialize() {
    this.cp.retract();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
