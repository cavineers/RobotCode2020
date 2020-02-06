/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousSelector extends InstantCommand {
  public AutonomousSelector(String potato) {
    String m_potato = potato;
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // The enum used as keys for selecting the command to run.

  private enum AutonomousSet {
    ONE, TWO, THREE
  }
  // An example selector method for the selectcommand.  Returns the selector that will select
  // which command to run.  Can base this choice on logical conditions evaluated at runtime.
  private AutonomousSet select() {
    return AutonomousSet.ONE;
  }
  
  // An example selectcommand.  Will select from the three commands based on the value returned
  // by the selector method at runtime.  Note that selectcommand works on Object(), so the
  // selector does not have to be an enum; it could be any desired type (string, integer,
  // boolean, double...)
  /* private final Command m_exampleSelectCommand =
      new SelectCommand(
          // Maps selector values to commands
          Map.ofEntries(
              entry(AutonomousSet.ONE, new PrintCommand("Command one was selected!")),
              entry(AutonomousSet.TWO, new PrintCommand("Command two was selected!")),
              entry(AutonomousSet.THREE, new PrintCommand("Command three was selected!"))
          ),
          this::select
      ); */
    
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
}
