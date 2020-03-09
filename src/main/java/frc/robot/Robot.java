package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.CLogger;
import frc.lib.Limelight;
import frc.robot.commands.Autonomous;
import frc.robot.commands.TeleopDrive;

public class Robot extends TimedRobot {
    // private Command autonomousCommand;

    private RobotContainer robotContainer;

    public static CLogger logger;

    public double lastLime;

    private boolean homeDrum = true;

    public Robot() {
        super(0.02); // Run the robot at 50Hz
    }

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();

        // ! THE LOG LEVEL SHOULD ALWAYS BE SET. UNCOMMENT EACH OF THE FOLLOWING LINE AFTER COMMENTING ALL
        // logger = new CLogger(CLogger.cLoggerMode.COMPETITION);
        // logger = new CLogger(CLogger.cLoggerMode.PRACTICE);
        logger = new CLogger(CLogger.cLoggerMode.TESTING);
        // logger = new CLogger(CLogger.cLoggerMode.DEVELOPMENT);

        SmartDashboard.putNumber("shooter_speed", 5500);

        SmartDashboard.putNumber("hood_angle", 0);

        // == DEFAULTS ==

        // Hood to low position
        // this.robotContainer.hood.turnToAngle(Hood.HoodAngle.LOW);
        this.robotContainer.hood.enable();

        // Shooter
        // this.robotContainer.shooter.enable();
        // this.robotContainer.shooter.setSpeed(0);

        // Turntable
        this.robotContainer.turnTable.disable();

        // Limelight
        this.robotContainer.limelight.setLightMode(Limelight.LEDMode.OFF);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if (Timer.getFPGATimestamp()-lastLime > .5) {
            lastLime = Timer.getFPGATimestamp();
            // Log the limelight distance
            // logger.logln("Distance: " + robotContainer.limelight.getDistance());
        }
        this.robotContainer.hood.hoodPeriodic();
        this.robotContainer.limelight.periodic();
        this.robotContainer.drum.DrumPeriodic();
        this.robotContainer.shooter.setSpeed(SmartDashboard.getNumber("shooter_speed", 0));
        this.robotContainer.dank.dankPeriodic();
    }

    @Override
    public void autonomousInit() {
        // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // // schedule the autonomous command (example)
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.schedule();
        // }

        this.robotContainer.drum.enable();
        this.robotContainer.hood.enable();
        this.robotContainer.drum.motor.setSelectedSensorPosition(0);
        this.robotContainer.drum.getController().setSetpoint(0);
        this.robotContainer.drum.setSetpoint(0);
        this.robotContainer.drum.currentSetpoint = 0;
        this.homeDrum = false;
        this.robotContainer.turnTable.tableMotor.setSelectedSensorPosition(0);

        // Autonomous command
        new Autonomous(this.robotContainer).schedule();
        // new Shoot(this.robotContainer).schedule();
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // if (m_autonomousCommand != null) {
        // m_autonomousCommand.cancel();
        // }

        // Home drum
        this.robotContainer.drum.enable();
        this.robotContainer.hood.enable();
        if (this.homeDrum) {
            this.robotContainer.drum.motor.setSelectedSensorPosition(0);
            this.robotContainer.drum.getController().setSetpoint(0);
            this.robotContainer.drum.setSetpoint(0);
            this.robotContainer.drum.currentSetpoint = 0;
        } else {
            this.homeDrum = true;
        }
        
        new TeleopDrive(this.robotContainer.drivetrain, this.robotContainer.joy).schedule();
    }

    @Override
    public void teleopPeriodic() {
        // Update controller data
        this.robotContainer.updateController();

        //! THIS IS FOR ML TRAINING
        this.robotContainer.hood.turnToAngle(SmartDashboard.getNumber("hood_angle", 0));
        // this.robotContainer.shooter.setSpeed(SmartDashboard.getNumber("shooter_speed", 0));
        SmartDashboard.putNumber("tx", this.robotContainer.limelight.getHorizontalOffset());
        SmartDashboard.putNumber("ty", this.robotContainer.limelight.getVerticalOffset());
        SmartDashboard.putNumber("td", this.robotContainer.limelight.getDistance());


        // this.robotContainer.feeder.setState(FeederState.ENABLED);
    }

    @Override
    public void disabledInit() {
        this.robotContainer.limelight.setLightMode(Limelight.LEDMode.OFF);
        this.robotContainer.drum.disable();
        this.robotContainer.hood.disable();
        this.robotContainer.shooter.disable();
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}
}
