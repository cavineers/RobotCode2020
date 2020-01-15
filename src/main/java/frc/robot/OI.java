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
import frc.robot.subsystems.Intake;

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
    public static JoystickButton left_middle = new JoystickButton(joy, 7);
    public static JoystickButton right_middle = new JoystickButton(joy, 8);
    public static JoystickButton left_stick = new JoystickButton(joy, 9);
    public static JoystickButton right_stick = new JoystickButton(joy, 10);

    public int lastDpad = -1;

    public OI() {

        // Button inputs for commands
        r_bump.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("r bumper");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }

        });
        // l_bump.whenPressed(new );

        // //actual button commands
        // a_button.whenPressed(new );
        // b_button.whenPressed(new );
        x_button.whileHeld((Command) new Intake());
        // y_button.whenPressed(new);

        // left_middle.whenPressed(new );
        // right_middle.whenPressed(new );

    }

    public boolean isRightTriggerPressed() {
        double rightTrig = this.getJoystick().getRawAxis(3);
        return rightTrig > 0.9;
    }

    public boolean isLeftTriggerPressed() {
        double leftTrig = this.getJoystick().getRawAxis(2);
        return leftTrig > 0.9;
    }

    public void updatePeriodicCommands() {

        if (lastDpad != joy.getPOV()) {
            switch (joy.getPOV()) {
            case 0: {
                // Top
                /*
                 * if(){
                 * 
                 * } else if(){
                 * 
                 * } else{
                 * 
                 * }
                 */
                break;
            }
            case 90: {
                // Right
                /*
                 * if(){
                 * 
                 * 
                 * } else if(){
                 * 
                 * } else{
                 * 
                 * }
                 */
                break;
            }
            case 180: {
                // Bottom
                /*
                 * if()){
                 * 
                 * } else if(){
                 * 
                 * } else{
                 * 
                 * }
                 */
                break;
            }
            case 270: {
                // Left
                /*
                 * if()){
                 * 
                 * } else if(){
                 * 
                 * } else{
                 * 
                 * }
                 */
                break;
            }
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
