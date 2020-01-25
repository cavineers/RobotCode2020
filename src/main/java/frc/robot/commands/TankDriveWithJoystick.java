/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDriveWithJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * @param subsystem The subsystem used by this command.
   */
  public TankDriveWithJoystick() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.getRightTalon().set(0);
    Robot.drivetrain.getLeftTalon().set(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
 /* @Override
  public void execute() {
    Robot.drivetrain.drive(Robot.m_robotContainer.getJoystick());
  }*/

  // Called once the command ends or is interrupted.
  //@Override
  //public void end(boolean interrupted) {
  //}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void end() {
    Robot.drivetrain.drive(0, 0);
  }
}