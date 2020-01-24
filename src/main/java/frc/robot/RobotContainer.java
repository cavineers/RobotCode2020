package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.lib.Deadzone;
import frc.lib.Limelight;

public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private DriveTrain drivetrain = new DriveTrain(this.getJoystick());
    private Turntable turnTable = new Turntable();
    private Limelight limelight = new Limelight();

    // Controller
    public static Joystick joy = new Joystick(0);
    public static JoystickButton a_button = new JoystickButton(joy, 1);
    public static JoystickButton b_button = new JoystickButton(joy, 2);
    public static JoystickButton x_button = new JoystickButton(joy, 3);
    public static JoystickButton y_button = new JoystickButton(joy, 4);

    public static JoystickButton l_bump = new JoystickButton(joy, 5);
    public static JoystickButton r_bump = new JoystickButton(joy, 6);
    public static JoystickButton left_menu = new JoystickButton(joy, 7);
    public static JoystickButton right_menu = new JoystickButton(joy, 8);
    public static JoystickButton left_stick = new JoystickButton(joy, 9);
    public static JoystickButton right_stick = new JoystickButton(joy, 10);

    public int lastDpad = -1;
    public boolean lastRightTrig = false;
    public boolean lastLeftTrig = false;

    public enum BUTTON_MODE {
        AUTO_SHOOT, CONTROL_P, CLIMB, NEUTRAL
    }

    public BUTTON_MODE currentTriggerSetting = BUTTON_MODE.NEUTRAL;

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
    }


    // public Command getAutonomousCommand() {
    //     // An ExampleCommand will run in autonomous
    //     return m_autoCommand;
    // }

    private boolean isRightTriggerPressed() {
        final double rightTrig = this.getJoystick().getRawAxis(3);
        return rightTrig > 0.9;
    }

    private boolean isLeftTriggerPressed() {
        final double leftTrig = this.getJoystick().getRawAxis(2);
        return leftTrig > 0.9;
    }

    public void updateController() {
        if (lastRightTrig != isRightTriggerPressed()) {
            // the right trigger changed state
            lastRightTrig = isRightTriggerPressed();
            if (lastRightTrig && currentTriggerSetting == BUTTON_MODE.CONTROL_P) {
                // the right trigger is pressed and we are in Control Panel mode

            } else if (lastRightTrig && currentTriggerSetting == BUTTON_MODE.CLIMB) {
                // the right trigger is pressed and we are in Climb mode
            } else if (lastRightTrig && currentTriggerSetting == BUTTON_MODE.NEUTRAL) {
                // the right trigger is pressed and we are in Neutral

            } else if (lastRightTrig && currentTriggerSetting == BUTTON_MODE.AUTO_SHOOT) {
                // the right trigger is pressed and we are in Auto Shoot mode

            }

        }

        if (lastLeftTrig != isLeftTriggerPressed()) {
            // the left trigger changed state
            lastLeftTrig = isLeftTriggerPressed();
            if (lastLeftTrig && currentTriggerSetting == BUTTON_MODE.CONTROL_P) {
                // the left trigger is pressed and we are in Control Panel mode

            } else if (lastLeftTrig && currentTriggerSetting == BUTTON_MODE.CLIMB) {
                // the left trigger is pressed and we are in Climb mode
            } else if (lastLeftTrig && currentTriggerSetting == BUTTON_MODE.NEUTRAL) {
                // the left trigger is pressed and we are in Neutral mode

            } else if (lastLeftTrig && currentTriggerSetting == BUTTON_MODE.AUTO_SHOOT) {
                // the left trigger is pressed and we are in Auto Shoot mode

            }
        }

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

    public Joystick getJoystick() {
        return joy;
    }
}
