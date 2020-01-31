package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TeleopDrive;

public class DriveTrain extends SubsystemBase {
    // Inst motors
    private CANSparkMax left1 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor1, MotorType.kBrushless);
    private CANSparkMax right1 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor2, MotorType.kBrushless);
    private CANSparkMax left2 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor3, MotorType.kBrushless);
    private CANSparkMax right2 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor4, MotorType.kBrushless);

    // Differential drive
    private DifferentialDrive differentialDrive;

    // Encoders
    CANEncoder leftEncoder = new CANEncoder(left1);
    CANEncoder rightEncoder = new CANEncoder(left2);

    // Gear mode
    public enum DriveGear {
        HIGH_GEAR,
        LOW_GEAR
    }

    private DriveGear currentDriveGear;
    
    // Gear shifting
    private DoubleSolenoid shiftingSol;

    public DriveTrain(Joystick joy) {
        // Add left1 & right1 to differential
        this.differentialDrive = new DifferentialDrive(left1, right1);
        
        // Invert the right side
        this.differentialDrive.setRightSideInverted(true);

        // Set default mode
        this.left1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.left2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.right1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        this.right2.setIdleMode(CANSparkMax.IdleMode.kBrake);

        // 2nd motors follow the 1st motors
        this.left2.follow(left1);
        this.right2.follow(right1);

        // Shift to low gear
        this.setDriveGear(DriveGear.LOW_GEAR);

        // Set default command
        setDefaultCommand(new TeleopDrive(this, joy));
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

    public void setDriveGear(DriveGear gear) {
        if (this.currentDriveGear == gear) return;
        switch (gear) {
            case HIGH_GEAR:
                shiftingSol.set(DoubleSolenoid.Value.kForward);
                this.currentDriveGear = DriveGear.HIGH_GEAR;
                break;
            case LOW_GEAR:
                shiftingSol.set(DoubleSolenoid.Value.kReverse);
                this.currentDriveGear = DriveGear.LOW_GEAR;
                break;
            default:
                // Do nothing if the value is not properly set
                break;
        }
    }

    public DriveGear getDriveGear() {
        return this.currentDriveGear;
    }
}
