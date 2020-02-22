/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;


public class AutonomousCommand1 extends SequentialCommandGroup {

  public AutonomousCommand1(DriveTrain dt, Drum d, Turntable tt, Limelight ll, Shooter s) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new AutoDrive(dt, 1, 0, 3), new AutoDrive(dt, 1, .5, 2), new DrumInit(d), new MoveIntoShootDistance(dt), 
          new AutoAlign(dt, tt, ll), new ShooterOn(s), new Shoot(ll, s), new Shoot(ll,s), new ShooterOff(s));
  }

}