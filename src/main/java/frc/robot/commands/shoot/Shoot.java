package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;

public class Shoot extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    private int stage = 0;

    private boolean finished = false;

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
        this.rc.shooter.enable();
        this.startTime = Timer.getFPGATimestamp();
        this.stage = 0;
    }

    @Override
    public void execute() {
        switch (stage) {
            case 0:
                if (this.rc.shooter.closeEnough()) {
                    this.stage = 1;
                    this.startTime = Timer.getFPGATimestamp();
                    this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                }
                break;
            case 1:
                if (Timer.getFPGATimestamp()-this.startTime > 3.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 2:
                if (Timer.getFPGATimestamp()-this.startTime > 6.5) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 3:
                if (Timer.getFPGATimestamp()-this.startTime > 10) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 4:
                if (Timer.getFPGATimestamp()-this.startTime > 13.5) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 5:
                if (Timer.getFPGATimestamp()-this.startTime > 17) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 6:
                this.finished = true;
                break;
            default:
                break;
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.shooter.disable();
        this.rc.feeder.setState(Feeder.FeederState.DISABLED);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
