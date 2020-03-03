package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.Limelight;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TurnTable;

public class AutoAlign extends SequentialCommandGroup {
    public AutoAlign(DriveTrain dt, TurnTable tt, Limelight ll) {
        // super(new FooCommand(), new BarCommand());
        // super(new MoveIntoShootDistance(dt));
        // super(new TurnTableToTarget(tt, ll.getHorizontalOffset()));
        Robot.logger.logln("Auto align");
    }
}
