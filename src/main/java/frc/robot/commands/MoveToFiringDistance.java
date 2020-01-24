/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.subsystems.DriveTrain;

public class MoveToFiringDistance extends CommandBase {
  private int wanted = 100;
  private int current;
  private Limelight limelight = new Limelight();
  private DriveTrain dt;

  public MoveToFiringDistance(DriveTrain dt) {
    addRequirements(dt);
    this.dt = dt;
  }

  @Override
  public void initialize() {
    this.current = limelight.getDistance();
  }

  @Override
  public void execute() {
    this.current = limelight.getDistance();
   
    this.dt.drive(0.1, 0, false);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if (Math.abs(this.wanted-this.current) <= 5) {
      return true;
    } else {
      return false;
    }
  }
}
