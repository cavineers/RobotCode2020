package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDriveWithJoystick;


public class DriveTrain extends SubsystemBase {
 
  public WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(Constants.kDriveTrainMotor1CANid);
  public WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(Constants.kDriveTrainMotor2CANid);
  
  public WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(Constants.kDriveTrainMotor3CANid);
  public WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(Constants.kDriveTrainMotor4CANid);
  
  private boolean brakeMode;
  private DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor1, rightMotor1);
  public DoubleSolenoid sol;

  public enum DriveGear{
    HIGH_GEAR, LOW_GEAR
  }
  
  public DriveTrain() {
    super();
    differentialDrive.setRightSideInverted(false);
    this.configTalons();
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);
    sol = new DoubleSolenoid(Constants.kPneumaticsControlCANid, Constants.kDriveShifter1, Constants.kDriveShifter2);
    this.setBrakeMode(false);
    this.setDriveGear(DriveGear.LOW_GEAR);
  }

  public void setDriveGear(DriveGear gear) {
    if(gear == DriveGear.LOW_GEAR) {
      sol.set(DoubleSolenoid.Value.kReverse);
    }else if(gear == DriveGear.HIGH_GEAR) {
      sol.set(DoubleSolenoid.Value.kForward);
    }
  }

  public DriveGear getDriveGear(){
    if(sol.get() == DoubleSolenoid.Value.kReverse) {
      return DriveGear.LOW_GEAR;
    }else{
      return DriveGear.HIGH_GEAR;
    }
  }

  public WPI_TalonSRX getLeftTalon() {
    return this.leftMotor1;
  }

  public WPI_TalonSRX getRightTalon() {
    return this.rightMotor1;
  }
  public void initDefaultCommand() {
      setDefaultCommand(new TankDriveWithJoystick());
  }

  public void configTalons() {
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);
    
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.kTimeoutMs);
    rightMotor1.setSensorPhase(true);
    
    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.kTimeoutMs);
    leftMotor1.setSensorPhase(true);

    rightMotor1.configNominalOutputForward(0, Constants.kTimeoutMs);
    rightMotor1.configNominalOutputReverse(0, Constants.kTimeoutMs);
    rightMotor1.configPeakOutputForward(1, Constants.kTimeoutMs);
    rightMotor1.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    leftMotor1.configNominalOutputForward(0, Constants.kTimeoutMs);
    leftMotor1.configNominalOutputReverse(0, Constants.kTimeoutMs);
    leftMotor1.configPeakOutputForward(1, Constants.kTimeoutMs);
    leftMotor1.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    rightMotor1.enableVoltageCompensation(true);
    rightMotor1.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
    rightMotor1.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.kTimeoutMs);
    rightMotor1.configVelocityMeasurementWindow(1, Constants.kTimeoutMs);
    rightMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 100);
    rightMotor1.configClosedloopRamp(Constants.kDriveVoltageRampRate, Constants.kTimeoutMs);

    leftMotor1.enableVoltageCompensation(true);
    leftMotor1.configVoltageCompSaturation(12.0, Constants.kTimeoutMs);
    leftMotor1.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.kTimeoutMs);
    leftMotor1.configVelocityMeasurementWindow(1, Constants.kTimeoutMs);
    leftMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 100);
    leftMotor1.configClosedloopRamp(Constants.kDriveVoltageRampRate, Constants.kTimeoutMs);

    rightMotor1.configClosedloopRamp(0.25);
    leftMotor1.configClosedloopRamp(0.25);
  }

  public void setBrakeMode(boolean on) {
    if(brakeMode != on) {
        brakeMode = on;
        NeutralMode mode = NeutralMode.Brake;
		  if(mode != NeutralMode.Brake){
          mode = NeutralMode.Brake;
        }else{
          mode = NeutralMode.Coast;
        }
        rightMotor1.setNeutralMode(mode);
        rightMotor2.setNeutralMode(mode);
        leftMotor1.setNeutralMode(mode);
        leftMotor2.setNeutralMode(mode);
        

    }
  }

  public double getLeftVelocity() {
    return this.leftMotor1.getSelectedSensorVelocity(0) / Constants.kDriveSensorUnitsPerInch * 10;
  }

  public void setLeftVel(double vel) {
    double accel = (vel - this.getLeftVelocity()) / Constants.kDefaultDt;
    vel += accel * Constants.kAVelocity;
    double targetVel = vel * Constants.kDriveSensorUnitsPerInch / 10;
    leftMotor1.set(ControlMode.Velocity, targetVel);
}

  public double getRightVelocity() {
    return this.leftMotor1.getSelectedSensorVelocity(0) / Constants.kDriveSensorUnitsPerInch * 10;
  } 

  public void setRightVelocity(double vel) {
    double accel = (vel - this.getRightVelocity()) / Constants.kDefaultDt;
    vel += accel * Constants.kAVelocity;
    double targetVel = vel * Constants.kDriveSensorUnitsPerInch / 10;
    rightMotor1.set(ControlMode.Velocity, targetVel);
  }

  public double getVelocity() {
    return this.getRightVelocity() + this.getLeftVelocity() / 2;
  }

  public double getLeftPosition() {
    return this.leftMotor1.getSelectedSensorPosition(0) / Constants.kDriveSensorUnitsPerInch;
  }

  public double getRightPosition() {
   return this.leftMotor1.getSelectedSensorPosition(0) / Constants.kDriveSensorUnitsPerInch;
  }

  public void drive(double forward, double rotate) {
    differentialDrive.curvatureDrive(forward, rotate, true);
  }

  public void drive(Joystick joy) {
    this.drive(this.addDeadZone(-joy.getRawAxis(1)), this.addDeadZone(joy.getRawAxis(4)));
  }

  public double addDeadZone(double input) {
    if(Math.abs(input) <= .05) {
      input = 0;
    }else if(input < 0){
      input = -Math.pow(input, 2);
    }else{
      input = Math.pow(input, 2);
    }
    return input;
  }

  public void zeroEncoders() {
    this.leftMotor1.setSelectedSensorPosition(0, 0, 0);
    this.rightMotor1.setSelectedSensorPosition(0, 0, 0);
  }

  @Override
  public void periodic() {
  }
}