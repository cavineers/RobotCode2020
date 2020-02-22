package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.CLogger;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Intake;

public class Robot extends TimedRobot {
    // private Command autonomousCommand;

    private RobotContainer robotContainer;

    public static CLogger logger;
    public static Intake intake;
    public static Drum drum;
    public double lastLime;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();

        // ! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE
        // AFTER COMMENTING ALL
        // logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
        // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
        logger = new CLogger(CLogger.cLoggerMode.TESTING);
        // logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);
        intake = new Intake();
        drum = new Drum();

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if (Robot.getCurrentTime() - lastLime > .5) {
            lastLime = Robot.getCurrentTime();
            // Log the limelight distance
            // logger.logln("Distance: " + robotContainer.limelight.getDistance());
        }
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // // schedule the autonomous command (example)
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.schedule();
        // }
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.cancel();
        // }
        this.robotContainer.teleInit();
    }

    @Override
    public void teleopPeriodic() {
        robotContainer.updateController();
        if (drum.numberOfBalls < 5) {
            intake.isOn = true;
            drum.drumReady = true;
        }

    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();

        logger.updateLogLevel(CLogger.cLoggerMode.TESTING);
    }

    @Override
    public void testPeriodic() {
    }

    public static double getCurrentTime() {
        return Timer.getFPGATimestamp();
    }
}
