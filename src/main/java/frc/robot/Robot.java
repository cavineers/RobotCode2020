package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.CLogger;
import frc.lib.Limelight;

public class Robot extends TimedRobot {
    // private Command autonomousCommand;

    private RobotContainer robotContainer;

    public static CLogger logger;

    public double lastLime;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();

        // ! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE AFTER COMMENTING ALL
        // logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
        // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
        logger = new CLogger(CLogger.cLoggerMode.TESTING);
        // logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);

        SmartDashboard.putNumber("hood_angle", 10);
        SmartDashboard.putNumber("shooter_speed", 0);

        // Turn off LEDs
        this.robotContainer.limelight.setLightMode(Limelight.LEDMode.ON);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if (Robot.getCurrentTime()-lastLime > .5) {
            lastLime = Robot.getCurrentTime();
            // Log the limelight distance
            // logger.logln("Distance: " + robotContainer.limelight.getDistance());
        }
        this.robotContainer.hood.hoodPeriodic();
        this.robotContainer.turnTable.turntablePeriodic();
        this.robotContainer.limelight.periodic();
    }

    @Override
    public void disabledInit() {
        this.robotContainer.turnTable.disable();
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {
        // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // // schedule the autonomous command (example)
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.schedule();
        // }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.cancel();
        // }
        this.robotContainer.teleInit();
        // this.robotContainer.hood.enable();
        // this.robotContainer.shooter.enable();
        // this.robotContainer.hood.enable();
    }

    @Override
    public void teleopPeriodic() {
        // Update controller data
        this.robotContainer.updateController();

        this.robotContainer.drum.DrumPeriodic();

        //! THIS IS FOR ML TRAINING
        this.robotContainer.hood.turnToAngle(SmartDashboard.getNumber("hood_angle", 0));
        this.robotContainer.shooter.setSpeed(SmartDashboard.getNumber("shooter_speed", 0));
        SmartDashboard.putNumber("tx", this.robotContainer.limelight.getHorizontalOffset());
        SmartDashboard.putNumber("ty", this.robotContainer.limelight.getVerticalOffset());
        SmartDashboard.putNumber("td", this.robotContainer.limelight.getDistance());

        if (this.robotContainer.joy.getRawAxis(2) > 0.05) {
            this.robotContainer.turnTable.tableMotor.set(-this.robotContainer.joy.getRawAxis(2)/2);
        } else {
            this.robotContainer.turnTable.tableMotor.set(this.robotContainer.joy.getRawAxis(3)/2);
        }
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();

        logger.updateLogLevel(CLogger.cLoggerMode.TESTING);
    }

    @Override
    public void testPeriodic() {}

    public static double getCurrentTime() {
        return Timer.getFPGATimestamp();
    }
}
