package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDriveWithJoystick;

public class DriveTrain extends SubsystemBase {

  public CANSparkMax leftMotor1 = new CANSparkMax(Constants.kDriveTrainMotor1CANid, MotorType.kBrushless);
  public CANSparkMax leftMotor2 = new CANSparkMax(Constants.kDriveTrainMotor2CANid, MotorType.kBrushless);
  public CANSparkMax rightMotor1 = new CANSparkMax(Constants.kDriveTrainMotor3CANid, MotorType.kBrushless);
  public CANSparkMax rightMotor2 = new CANSparkMax(Constants.kDriveTrainMotor4CANid, MotorType.kBrushless);

  CANEncoder leftEncoder = new CANEncoder(leftMotor1);
  CANEncoder rightEncoder = new CANEncoder(rightMotor1);

  private boolean brakeMode;
  private DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor1, rightMotor1);
  public DoubleSolenoid sol;

  public enum DriveGear {
    HIGH_GEAR, 
    LOW_GEAR
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
    leftMotor1.setSmartCurrentLimit(28);
    leftMotor2.setSmartCurrentLimit(28);
    rightMotor1.setSmartCurrentLimit(28);
    rightMotor2.setSmartCurrentLimit(28);
  }

  public void setDriveGear(DriveGear gear) {
    if (gear == DriveGear.LOW_GEAR) {
      sol.set(DoubleSolenoid.Value.kReverse);
    } else if (gear == DriveGear.HIGH_GEAR) {
      sol.set(DoubleSolenoid.Value.kForward);
    }
  }

  public DriveGear getDriveGear() {
    if (sol.get() == DoubleSolenoid.Value.kReverse) {
      return DriveGear.LOW_GEAR;
    } else {
      return DriveGear.HIGH_GEAR;
    }
  }

  public CANSparkMax getLeftSparkMax() {
    return this.leftMotor1;
  }

  public CANSparkMax getRightSparkMax() {
    return this.rightMotor1;
  }

  public void initDefaultCommand(Joystick joy) {
    setDefaultCommand(new TankDriveWithJoystick(this, joy));
  }

  public void configTalons() {
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);

    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
  }

  public void setBrakeMode(boolean on) {
    if(brakeMode != on) {
        brakeMode = on;
        IdleMode mode = IdleMode.kBrake;
      if (mode != IdleMode.kBrake) {
        mode = IdleMode.kBrake;
      }
        rightMotor1.setIdleMode(mode);
        rightMotor2.setIdleMode(mode);
        leftMotor1.setIdleMode(mode);
        leftMotor2.setIdleMode(mode);
        
    }
  }

  public double getLeftVelocity() {
    return this.leftEncoder.getVelocity() / Constants.kDriveSensorUnitsPerInch * 10;
  }

  public void setLeftVel(double vel) {
    double accel = (vel - this.getLeftVelocity()) / Constants.kDefaultDt;
    vel += accel * Constants.kAVelocity;
    double targetVel = vel * Constants.kDriveSensorUnitsPerInch / 10;
    leftMotor1.set(targetVel);
  }

  public double getRightVelocity() {
    return this.rightEncoder.getVelocity() / Constants.kDriveSensorUnitsPerInch * 10;
  } 

  public void setRightVelocity(double vel) {
    double accel = (vel - this.getRightVelocity()) / Constants.kDefaultDt;
    vel += accel * Constants.kAVelocity;
    double targetVel = vel * Constants.kDriveSensorUnitsPerInch / 10;
    rightMotor1.set(targetVel);
  }

  public double getVelocity() {
    return this.getRightVelocity() + this.getLeftVelocity() / 2;
  }

  public double getLeftPosition() {
    return this.leftEncoder.getPosition() / Constants.kDriveSensorUnitsPerInch * 10;
  }

  public double getRightPosition() {
    return this.leftEncoder.getPosition() / Constants.kDriveSensorUnitsPerInch * 10;
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
    this.leftEncoder.setPosition(0);
    this.rightEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
  }
}