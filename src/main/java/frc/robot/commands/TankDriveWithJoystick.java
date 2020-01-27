/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDriveWithJoystick extends CommandBase {
  private DriveTrain drivetrain;
  private Joystick joy;

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * @param subsystem The subsystem used by this command.
   */
  public TankDriveWithJoystick(DriveTrain drivetrain, Joystick joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.joy = joy;
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.getRightTalon().set(0);
    drivetrain.getLeftTalon().set(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
 /* @Override
  public void execute() {
    Robot.drivetrain.drive(Robot.m_robotContainer.getJoystick());
  }*/


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void end() {
    drivetrain.drive(0, 0);
  }
}