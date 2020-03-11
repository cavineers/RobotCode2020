package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.TurnTable;

public class HighShooter extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    private int stage = 0;

    private boolean finished = false;

    public HighShooter(RobotContainer rc) {
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
        this.rc.turnTable.setState(TurnTable.TurnTableState.TARGETING);
        SmartDashboard.putNumber("shooter_speed", 5500);
        SmartDashboard.putNumber("hood_angle", 20);
    }

    @Override
    public void execute() {
        switch (stage) {
            case 0:
                if (this.rc.limelight.getHorizontalOffset() < 2.0) {
                    this.stage = 1;
                }
                break;
            case 1:
                if (this.rc.shooter.closeEnough()) {
                    this.stage = 2;
                    this.startTime = Timer.getFPGATimestamp();
                    this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                    log();
                }
                break;
            case 2:
                if (Timer.getFPGATimestamp()-this.startTime > 0.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 3;
                    log();
                }
                break;
            case 3:
                if (Timer.getFPGATimestamp()-this.startTime > 2.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 4;
                    log();
                }
                break;
            case 4:
                if (Timer.getFPGATimestamp()-this.startTime > 4.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 5;
                    log();
                }
                break;
            case 5:
                if (Timer.getFPGATimestamp()-this.startTime > 6.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 6;
                    log();
                }
                break;
            case 6:
                if (Timer.getFPGATimestamp()-this.startTime > 8.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 7;
                    log();
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
        this.rc.turnTable.setState(TurnTable.TurnTableState.NEUTRAL);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    public void log() {
        System.out.println(this.rc.limelight.getHorizontalOffset() + "," + this.rc.limelight.getVerticalOffset() + "," + this.rc.limelight.getDistance());
    }
}
