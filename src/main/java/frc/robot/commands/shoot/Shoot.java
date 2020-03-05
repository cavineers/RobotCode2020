package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.FeederOn;

public class Shoot extends CommandBase {
    private RobotContainer rc;
    public Shoot(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        // new LimelightOn(this.rc.limelight).andThen(
        //     new PerfectTurnTable(this.rc.turnTable, this.rc.limelight).andThen(
        //         new ShooterOn(this.rc.shooter)
        //     )
        // );
        System.out.println("Started Shooter");
        new ShooterOn(this.rc.shooter).schedule();
        new FeederOn(this.rc.feeder).schedule();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
