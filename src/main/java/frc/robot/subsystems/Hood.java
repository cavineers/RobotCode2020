package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hood extends SubsystemBase {
    // Motor
    private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Hood.MotorID);

    // Limit switch
    private DigitalInput limitSwitch = new DigitalInput(Constants.Hood.LimitSwitch);

    // Homing status
    private boolean isHoming = false;
    private boolean isHomed = false;

    // Current angle
    private double currentAngle = 0.0;

    private double lastPrint = 0.0;

    /**
     * Constructor
     */
    public Hood() {}

    /**
     * home the hood
     */
    public void home() {
        this.isHoming = true;
        this.motor.set(ControlMode.PercentOutput, Constants.Hood.HomingSpeed);
    }

    /**
     * set the desired angle of the hood (from the limit switch, not horizontal plane)
     * @param angle
     */
    public void setAngle(double angle) {
        if (this.isHomed) {
            this.currentAngle = angle;
            // do some angle math to set motor to
        }
    }

    /**
     * get the hood's current angle from the current limit switch
     * @return current angle
     */
    public double getAngle() {
        return  this.currentAngle;
    }

    /**
     * Hood periodic
     */
    @Override
    public void periodic() {
        // Print limit switch value every half second
        if (Timer.getFPGATimestamp()-this.lastPrint > 0.5) {
            System.out.println("Hood Limit Switch: " + this.limitSwitch.get());
            this.lastPrint = Timer.getFPGATimestamp();
        }
        // Homing method
        if (this.isHoming) {
            if (!this.limitSwitch.get()) {
                this.motor.set(ControlMode.PercentOutput, 0);
                this.isHomed = true;
                this.isHoming = false;
                this.currentAngle = 0.0;
            }
        }
    }
}