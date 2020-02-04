package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.CLogger;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turntable;

public class Robot extends TimedRobot {
    // private Command autonomousCommand;

    private RobotContainer robotContainer;

    public static CLogger logger;

    public double lastLime;

    // protected Robot() {
    //     super(0.00000002); // 50 MHz //^ This is how we yeet the robot
    // }

    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();

        // ! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE
        // AFTER COMMENTING ALL
        // logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
        // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
        // logger = new CLogger(CLogger.cLoggerMode.TESTING);
        logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        // System.out.println("Climber Current: " + robotContainer.climber.climberMotor.getOutputCurrent());
        if (Robot.getCurrentTime()-lastLime > .5) {
            lastLime = Robot.getCurrentTime();
            // System.out.println("Distance: " + robotContainer.limelight.getDistance());
        }
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
        this.robotContainer.teleInit();
        // this.robotContainer.turnTable.setMotorRotation(Turntable.TurntableMode.ROTATE_LEFT);
    }

    @Override
    public void teleopPeriodic() {
        robotContainer.updateController();
        // this.flyWheel.set(-100);
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

    public static double getCurrentTime() {
        return Timer.getFPGATimestamp();
    }
}
