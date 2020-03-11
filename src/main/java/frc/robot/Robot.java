package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.Limelight;
import frc.robot.commands.Autonomous;
import frc.robot.commands.HomeHood;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TurnTable;

public class Robot extends TimedRobot {
    // Robot container
    private RobotContainer robotContainer;

    public double lastLime;

    private boolean homeDrum = true;

    public Robot() {
        super(0.02); // Run the robot at 50Hz
    }

    @Override
    public void robotInit() {
        this.robotContainer = new RobotContainer();

        // == DEFAULTS ==

        // Hood
        this.robotContainer.hood.enable();
        SmartDashboard.putNumber("hood_angle", 0);

        // Turntable
        this.robotContainer.turnTable.setState(TurnTable.TurnTableState.NEUTRAL);

        // Limelight
        this.robotContainer.limelight.setLightMode(Limelight.LEDMode.OFF);

        // Shooter
        SmartDashboard.putNumber("shooter_speed", 5500);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if (Timer.getFPGATimestamp()-lastLime > .5) {
            lastLime = Timer.getFPGATimestamp();
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
        this.robotContainer.hood.enable();
        this.robotContainer.drum.enable();
        this.robotContainer.drum.motor.setSelectedSensorPosition(0);
        this.robotContainer.drum.getController().setSetpoint(0);
        this.robotContainer.drum.setSetpoint(0);
        this.robotContainer.drum.currentSetpoint = 0;
        this.homeDrum = false;
        this.robotContainer.turnTable.tableMotor.setSelectedSensorPosition(0);

        // Home hood
        new HomeHood(this.robotContainer.hood).schedule();

        // Autonomous command
        new Autonomous(this.robotContainer).schedule();
        // new Shoot(this.robotContainer).schedule();
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // Home drum
        this.robotContainer.drum.enable();
        if (this.homeDrum) {
            this.robotContainer.drum.motor.setSelectedSensorPosition(0);
            this.robotContainer.drum.getController().setSetpoint(0);
            this.robotContainer.drum.setSetpoint(0);
            this.robotContainer.drum.currentSetpoint = 0;
        } else {
            this.homeDrum = true;
        }

        // Home hood
        this.robotContainer.hood.enable();

        // Extend pistons
        this.robotContainer.intake.setPistonState(Intake.IntakePistonState.EXTENDED);
        
        // Start drive
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
        this.robotContainer.intake.setMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}
}
