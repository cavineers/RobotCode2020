package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
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
        this.rc.limelight.setLightMode(Limelight.LEDMode.ON);
        this.rc.turnTable.enable();
    }

    @Override
    public void execute() {
        switch (stage) {
            case 0:
                if (this.rc.limelight.getHorizontalOffset() < 2.0) {
                    this.stage++;
                }
                break;
            case 1:
                if (this.rc.shooter.closeEnough()) {
                    this.stage++;
                    this.startTime = Timer.getFPGATimestamp();
                    this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                }
                break;
            case 2:
                if (Timer.getFPGATimestamp()-this.startTime > 0.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 3:
                if (Timer.getFPGATimestamp()-this.startTime > 2.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 4:
                if (Timer.getFPGATimestamp()-this.startTime > 4.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 5:
                if (Timer.getFPGATimestamp()-this.startTime > 6.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 6:
                if (Timer.getFPGATimestamp()-this.startTime > 8.0) {
                    this.rc.drum.moveToNext();
                    this.stage++;
                }
                break;
            case 7:
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
        this.rc.limelight.setLightMode(Limelight.LEDMode.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
