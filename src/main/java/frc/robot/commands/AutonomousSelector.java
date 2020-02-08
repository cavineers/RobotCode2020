package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.lib.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Turntable;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousSelector extends InstantCommand {
  DriveTrain dt;
  Drum d;
  Turntable t;
  Limelight ll;
  AutonomousCommand1 auto1 = new AutonomousCommand1(dt, d, t, ll);
  AutonomousCommand2 auto2 = new AutonomousCommand2(dt);
  AutonomousCommand3 auto3 = new AutonomousCommand3(dt);

  public AutonomousSelector() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // The enum used as keys for selecting the command to run.

  private enum AutonomousSet {
    ONE, TWO, THREE, NONE
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
  private void selectCommand(){
      new SelectCommand(
          // Maps selector values to commands
          Map.ofEntries(
              Map.entry(AutonomousSet.ONE, new PrintCommand("Command one was selected!")),
              Map.entry(AutonomousSet.TWO, new PrintCommand("Command two was selected!")),
              Map.entry(AutonomousSet.THREE, new PrintCommand("Command three was selected!"))
          ),
          this::select
      ); }
    
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    selectCommand();
    if (select() == AutonomousSet.ONE) {
      auto1.execute();
    } else if (select() == AutonomousSet.TWO) {
      auto2.execute();
    } else if (select() == AutonomousSet.THREE) {
      auto3.execute();
   }
  }
}