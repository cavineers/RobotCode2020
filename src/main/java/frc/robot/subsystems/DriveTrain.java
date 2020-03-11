package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    // Inst motors
    public CANSparkMax left1  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor1, MotorType.kBrushless);
    public CANSparkMax right1 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor2, MotorType.kBrushless);
    public CANSparkMax left2  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor3, MotorType.kBrushless);
    public CANSparkMax right2 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor4, MotorType.kBrushless);

    // Differential drive
    private DifferentialDrive differentialDrive;

    // Encoders
    CANEncoder leftEncoder = new CANEncoder(left1);
    CANEncoder rightEncoder = new CANEncoder(right1);

    // Gear mode
    public enum DriveGear {
        HIGH_GEAR,
        LOW_GEAR
    }

    public enum DriveCoastBrake {
        COAST,
        BRAKE
    }

    private DriveGear currentDriveGear;

    private DriveCoastBrake currentCoastBrake;
    
    // Gear shifting
    private DoubleSolenoid shiftingSol = new DoubleSolenoid(Constants.DriveTrain.PCMChannel1, Constants.DriveTrain.PCMChannel2);

    public DriveTrain(Joystick joy) {
        // Add left1 & right1 to differential
        this.differentialDrive = new DifferentialDrive(left1, right1);
        
        // Invert the right side
        this.differentialDrive.setRightSideInverted(true);

        // Factory Defaults
        this.left1.restoreFactoryDefaults();
        this.left2.restoreFactoryDefaults();
        this.right1.restoreFactoryDefaults();
        this.right2.restoreFactoryDefaults();

        // Current limiting
        this.left1.setSmartCurrentLimit(Constants.DriveTrain.CurrentLimit);
        this.left2.setSmartCurrentLimit(Constants.DriveTrain.CurrentLimit);
        this.right1.setSmartCurrentLimit(Constants.DriveTrain.CurrentLimit);
        this.right2.setSmartCurrentLimit(Constants.DriveTrain.CurrentLimit);

        // Set default mode

        // 2nd motors follow the 1st motors
        this.left2.follow(left1);
        this.right2.follow(right1);

        // Shift to low gear
        this.setDriveGear(DriveGear.LOW_GEAR);

        // Set the drivetrain to coast mode
        this.setCoastBrakeMode(DriveCoastBrake.COAST);
    }

    /**
     * drive
     * @param drive Forward/reverse value
     * @param steer Left/right value
     * @param quickTurn Quick turning
     */
    public void drive(double drive, double steer, boolean quickTurn) {
        // Pass values to the differential drive
        this.differentialDrive.curvatureDrive(drive, steer, quickTurn);
    }

    /**
     * drive
     * @param drive Forward/reverse value
     * @param steer Left/right value
     */
    public void drive(double drive, double steer) {
        // If no quickTurn is set, default to false
        this.differentialDrive.curvatureDrive(drive, steer, false);
    }

    /**
     * set the drive gear
     * @param gear wanted drive gear
     */
    public void setDriveGear(DriveGear gear) {
        if (this.currentDriveGear == gear) return;
        switch (gear) {
            case HIGH_GEAR:
                // Switch to high
                this.shiftingSol.set(DoubleSolenoid.Value.kForward);
                this.currentDriveGear = DriveGear.HIGH_GEAR;
                break;
            case LOW_GEAR:
                // Switch to low
                this.shiftingSol.set(DoubleSolenoid.Value.kReverse);
                this.currentDriveGear = DriveGear.LOW_GEAR;
                break;
            default:
                // Do nothing if the value is not properly set
                break;
        }
    }

    /**
     * get the current drive gear
     * @return the current drive gear
     */
    public DriveGear getDriveGear() {
        return this.currentDriveGear;
    }

    /**
     * Get the differential drive
     * @return differential drive
     */
    public DifferentialDrive getDifferentialDrive() {
        return this.differentialDrive;
    }

    /**
     * Set the brake and coast mode
     * @param mode coast / brake
     */
    public void setCoastBrakeMode(DriveCoastBrake mode) {
        this.currentCoastBrake = mode;
        CANSparkMax.IdleMode idleMode = mode == DriveCoastBrake.BRAKE ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast;
        this.left1.setIdleMode(idleMode);
        this.left2.setIdleMode(idleMode);
        this.right1.setIdleMode(idleMode);
        this.right2.setIdleMode(idleMode);
    }

    /**
     * Get the brake and coast mode
     * @return current mode
     */
    public DriveCoastBrake getCoastBrakeMode() {
        return this.currentCoastBrake;
    }

    /**
     * DriveTrain periodic
     */
    @Override
    public void periodic() {}
}
