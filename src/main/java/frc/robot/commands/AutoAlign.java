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
import frc.robot.subsystems.Turntable;

public class AutoAlign extends SequentialCommandGroup {
    public AutoAlign(DriveTrain dt, Turntable tt, Limelight ll) {
        // super(new FooCommand(), new BarCommand());
        // super(new MoveIntoShootDistance(dt));
        // super(new TurntableToTarget(tt, ll.getHorizontalOffset()));
        System.out.println("Auto align");
    }
}
