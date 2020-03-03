package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Shoot extends CommandBase {
    private RobotContainer rc;
    public Shoot(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        new LimelightOn(this.rc.limelight).andThen(
            new PerfectTurnTable(this.rc.turnTable, this.rc.limelight).andThen(
                new ShooterOn(this.rc.shooter)
            )
        );
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
