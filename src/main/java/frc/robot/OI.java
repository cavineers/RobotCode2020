/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Set;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

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

    public OI() {

        // if (currentTriggerSetting == BUTTON_MODE.CONTROL_P) {
        // // we are in Control Panel mode
        // r_bump.whenPressed(new Command());
        // l_bump.whenPressed(new Command());
        // a_button.whenPressed(new Command());
        // b_button.whenPressed(new Command());
        // x_button.whenPressed(new Command());
        // y_button.whenPressed(new Command());

        // left_menu.whenPressed(new Command());
        // right_menu.whenPressed(new Command());
        // right_stick.whenPressed(new Command());
        // left_stick.whenPressed(new Command());
        // } else if (currentTriggerSetting == BUTTON_MODE.CLIMB) {
        // // we are in Climb mode
        // r_bump.whenPressed(new Command());
        // l_bump.whenPressed(new Command());
        // a_button.whenPressed(new Command());
        // b_button.whenPressed(new Command());
        // x_button.whenPressed(new Command());
        // y_button.whenPressed(new Command());

        // left_menu.whenPressed(new Command());
        // right_menu.whenPressed(new Command());
        // right_stick.whenPressed(new Command());
        // left_stick.whenPressed(new Command());
        // } else if (currentTriggerSetting == BUTTON_MODE.NEUTRAL) {
        // // we are in Neutral
        // r_bump.whenPressed(new Command());
        // l_bump.whenPressed(new Command());
        // a_button.whenPressed(new Command());
        // b_button.whenPressed(new Command());
        // x_button.whenPressed(new Command());
        // y_button.whenPressed(new Command());

        // left_menu.whenPressed(new Command());
        // right_menu.whenPressed(new Command());
        // right_stick.whenPressed(new Command());
        // left_stick.whenPressed(new Command());
        // } else if (currentTriggerSetting == BUTTON_MODE.AUTO_SHOOT) {
        // // we are in Auto Shoot mode
        // r_bump.whenPressed(new Command());
        // l_bump.whenPressed(new Command());
        // a_button.whenPressed(new Command());
        // b_button.whenPressed(new Command());
        // x_button.whenPressed(new Command());
        // y_button.whenPressed(new Command());

        // left_menu.whenPressed(new Command());
        // right_menu.whenPressed(new Command());
        // right_stick.whenPressed(new Command());
        // left_stick.whenPressed(new Command());
        // }
        String rightBump = "r_bump";
        String leftBump = "l_bump";
        String aButton = "a_button";
        String bButton = "b_button";
        String xButton = "x_button";
        String yButton = "y_button";

        String leftMenu = "left_menu";
        String rightMenu = "right_menu";
        String rightStick = "right_stick";
        String leftStick = "left_stick";

        // Button inputs for commands
        r_bump.whenPressed(new PrintButton(rightBump));
        l_bump.whenPressed(new PrintButton(leftBump));
        a_button.whenPressed(new PrintButton(aButton));
        b_button.whenPressed(new PrintButton(bButton));
        x_button.whenPressed(new PrintButton(xButton));
        y_button.whenPressed(new PrintButton(yButton));

        left_menu.whenPressed(new PrintButton(leftMenu));
        right_menu.whenPressed(new PrintButton(rightMenu));
        right_stick.whenPressed(new PrintButton(rightStick));
        left_stick.whenPressed(new PrintButton(leftStick));

    }

    public boolean isRightTriggerPressed() {
        final double rightTrig = this.getJoystick().getRawAxis(3);
        return rightTrig > 0.9;
    }

    public boolean isLeftTriggerPressed() {
        final double leftTrig = this.getJoystick().getRawAxis(2);
        return leftTrig > 0.9;
    }

    public void updatePeriodicCommands() {

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

    public double addDeadZone(double input) {
        if (Math.abs(input) <= .05)
            input = 0;
        else if (input < 0)
            input = -Math.pow(input, 2);
        else
            input = Math.pow(input, 2);
        return input;
    }

}
