package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.CLogger;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turntable;

public class Robot extends TimedRobot {
    private Command autonomousCommand;

    private RobotContainer robotContainer;

    public static CLogger logger;

    public static DriveTrain driveTrain;

    public static Turntable turnTable;

    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();

        // ! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE
        // AFTER COMMENTING ALL
        logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
        // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
        // logger = new CLogger(CLogger.cLoggerMode.TESTING);
        // logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);

        turnTable = new Turntable();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {}

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
    }

    @Override
    public void teleopPeriodic() {
        robotContainer.updateController();
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();

        // Switch logging to test mode
        logger.updateLogLevel(CLogger.cLoggerMode.TESTING);
    }

    @Override
    public void testPeriodic() {}
}
