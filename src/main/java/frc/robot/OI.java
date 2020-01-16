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
import frc.robot.commands.turnIntakeOn;
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
    public static JoystickButton left_menu = new JoystickButton(joy, 7);
    public static JoystickButton right_menu = new JoystickButton(joy, 8);
    public static JoystickButton left_stick = new JoystickButton(joy, 9);
    public static JoystickButton right_stick = new JoystickButton(joy, 10);

    public int lastDpad = -1;


    public enum BUTTON_MODE {
        AUTO_SHOOT, CONTROL_P, CLIMB, NEUTRAL
    }

    public BUTTON_MODE currentTriggerSetting = BUTTON_MODE.NEUTRAL;


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
        l_bump.whenPressed(new Command() {

        x_button.whenHeld(new turnIntakeOn());
            @Override
            public void initialize() {
                System.out.println("l bumper");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        a_button.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("a button");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        b_button.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("b button");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }

       
        });
=
        x_button.whileHeld((Command) new Intake());
        

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        y_button.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("y button");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });

        left_menu.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("start menu");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        right_menu.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("scoreboard");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        right_stick.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("R3");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        left_stick.whenPressed(new Command() {

            @Override
            public void initialize() {
                System.out.println("L3");
            }

            @Override
            public Set<Subsystem> getRequirements() {
                // TODO Auto-generated method stub
                return null;
            }
        });

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

                currentTriggerSetting = BUTTON_MODE.CONTROL_P;
                System.out.println("In Control Panel mode");

                break;
            }
            case 90: {
                // Right

                currentTriggerSetting = BUTTON_MODE.CLIMB;
                System.out.println("In Climb mode");

                break;
            }
            case 180: {
                // Bottom

                currentTriggerSetting = BUTTON_MODE.NEUTRAL;
                System.out.println("In Neutral mode");

              


                currentTriggerSetting = BUTTON_MODE.AUTO_SHOOT;
                System.out.println("In Auto Shoot mode");
                break;
            }
            default: {
                System.out.println("Nothing is pressed, hopefully");
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
