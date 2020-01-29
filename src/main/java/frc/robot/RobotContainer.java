/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static Joystick joy = new Joystick(0);
  public int lastDpad = -1;

  public enum BUTTON_MODE {
    AUTO_SHOOT, CONTROL_P, CLIMB, NEUTRAL
  }

  public BUTTON_MODE currentTriggerSetting = BUTTON_MODE.NEUTRAL;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  public void ControllerPeriodic(){
    if (lastDpad != joy.getPOV()) {
      switch (joy.getPOV()) {
      case 0:
        // Top
        currentTriggerSetting = BUTTON_MODE.CONTROL_P;
        System.out.println("In Control Panel mode");
        break;
      case 90:
        // Right
        currentTriggerSetting = BUTTON_MODE.CLIMB;
        System.out.println("In Climb mode");
        break;
      case 180:
        // Bottom
        currentTriggerSetting = BUTTON_MODE.NEUTRAL;
        System.out.println("In Neutral mode");
        break;
      case 270:
        currentTriggerSetting = BUTTON_MODE.AUTO_SHOOT;
        System.out.println("In Auto Shoot mode");
        break;
      default:
        System.out.println("Nothing is pressed, hopefully");
        break;
      }
    }
    lastDpad = joy.getPOV();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
