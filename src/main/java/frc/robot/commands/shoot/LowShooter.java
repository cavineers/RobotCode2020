package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Feeder;

public class LowShooter extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    private int stage = 0;

    private boolean finished = false;

    public LowShooter(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        System.out.println("Started Shooter");
        this.rc.shooter.enable();
        this.startTime = Timer.getFPGATimestamp();
        this.stage = 1;
        SmartDashboard.putNumber("shooter_speed", 2500);
        SmartDashboard.putNumber("hood_angle", 30);
    }

    @Override
    public void execute() {
        switch (stage) {
            case 1:
                if (this.rc.shooter.closeEnough()) {
                    this.stage = 2;
                    this.startTime = Timer.getFPGATimestamp();
                    this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                    log();
                }
                break;
            case 2:
                this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                if (Timer.getFPGATimestamp()-this.startTime > 4.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 3;
                    log();
                }
                break;
            case 3:
                this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                if (Timer.getFPGATimestamp()-this.startTime > 6.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 4;
                    log();
                }
                break;
            case 4:
                this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                if (Timer.getFPGATimestamp()-this.startTime > 8.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 5;
                    log();
                }
                break;
            case 5:
                this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                if (Timer.getFPGATimestamp()-this.startTime > 10.0) {
                    this.rc.drum.moveToNext();
                    this.stage = 6;
                    log();
                }
                break;
            case 6:
                this.rc.feeder.setState(Feeder.FeederState.ENABLED);
                if (Timer.getFPGATimestamp()-this.startTime > 12.0) {
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
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    public void log() {
        System.out.println(this.rc.limelight.getHorizontalOffset()+","+this.rc.limelight.getVerticalOffset()+","+this.rc.limelight.getDistance());
    }
}
