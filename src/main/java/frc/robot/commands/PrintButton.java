/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PrintButton extends InstantCommand {
  String m_buttonpress;

  public PrintButton(String buttonpress) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_buttonpress = buttonpress;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("The Button you pressed is " + m_buttonpress);
  }
}
