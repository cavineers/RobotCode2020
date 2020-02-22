package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.lib.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;


public class AutonomousSelector extends InstantCommand {
  DriveTrain dt;
  Drum d;
  Turntable tt;
  Limelight ll;
  Shooter s;
  AutonomousCommand1 auto1 = new AutonomousCommand1(dt, d, tt, ll, s);
  AutonomousCommand2 auto2 = new AutonomousCommand2(dt, d, tt, ll, s);
  AutonomousCommand3 auto3 = new AutonomousCommand3(dt, d, tt, ll, s);

  public AutonomousSelector(DriveTrain dt, Drum d, Turntable tt, Limelight ll, Shooter s) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dt = dt;
    this.d = d;
    this.tt = tt;
    this.ll = ll;
    this.s = s;
    addRequirements(dt);
    addRequirements(d);
    addRequirements(tt);
    addRequirements(s);
  }

  // The enum used as keys for selecting the command to run.
  private enum AutonomousSet {
    ONE, TWO, THREE, NONE
  }

  private AutonomousSet select() {
    //return AutonomousSet.ONE;
    //return AutonomousSet.TWO;
    //return AutonomousSet.THREE;
    return AutonomousSet.NONE;
  }
  
  @Override
  public void initialize() {
    if (select() == AutonomousSet.ONE) {
      auto1.execute();
      System.out.println("Command one");
    } else if (select() == AutonomousSet.TWO) {
      auto2.execute();
      System.out.println("Command two");
    } else if (select() == AutonomousSet.THREE) {
      auto3.execute();
      System.out.println("Command three");
    } else {
      System.out.println("No command");
   }
  }
}