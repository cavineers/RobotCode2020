/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.lib.CLogger;
import frc.lib.Limelight;
import edu.wpi.first.wpilibj.Timer;
// import frc.robot.subsystems.DriveTrain;
import frc.robot.OI;
// import frc.robot.commands.TargetDistance;


public class Robot extends TimedRobot {
  private static Command m_autonomousCommand;

  private static RobotContainer m_robotContainer;

  public static CLogger logger;
  public static Limelight limelight;

  private static double lastLime = 0;

  // public static DriveTrain drivetrain;

  public static OI oi;

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE AFTER COMMENTING ALL
    logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
    // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
    // logger = new CLogger(CLogger.cLoggerMode.TESTING);
    // logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);

    // Limelight
    this.limelight = new Limelight();

    this.lastLime = Robot.getCurrentTime();

    // DriveTrain
    // this.drivetrain = new DriveTrain();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // System.out.println("Horizontal: " + limelight.getHorizontalOffset());
    // System.out.println("Vertical: " + limelight.getVerticalOffset());
    // System.out.println("Distance: " + limelight.getDistance());
    if (Robot.getCurrentTime()-this.lastLime > .5) {
        this.lastLime = Robot.getCurrentTime();
        // System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0));
        System.out.println("Distance: " + limelight.getDistance());
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
    //   m_autonomousCommand.schedule();
    // }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
      // new TargetDistance(drivetrain, 150);
      // if (limelight.getDistance() < 150) {
      //   System.out.println("driving");
      //   this.drivetrain.drive(0, 0);
      // } else {
      //   System.out.println("stopping");
      //   this.drivetrain.drive(0.1, 0);
      // }
  }

  @Override
    public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    // Switch logging to test mode
    logger.updateLogLevel(CLogger.cLoggerMode.TESTING);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public static double getCurrentTime() {
    return Timer.getFPGATimestamp();
  }
}
