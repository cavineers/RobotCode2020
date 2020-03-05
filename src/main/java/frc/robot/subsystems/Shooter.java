package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    // Fly wheel motor
    private CANSparkMax shootMotor = new CANSparkMax(Constants.Shooter.ShootID, MotorType.kBrushless);

    // Fly wheel PID
    private CANPIDController pidController = shootMotor.getPIDController();
    
    // Fly wheel encoder
    private CANEncoder encoder = shootMotor.getEncoder();

    // Set initial PID values
    public double kP = Constants.Shooter.PIDp;
    public double kI = Constants.Shooter.PIDi;
    public double kD = Constants.Shooter.PIDd;
    
    // Shooter Mode
    public enum ShooterMode {
        ENABLED,
        DISABLED
    }

    // Current shooter mode
    private ShooterMode currentMode = ShooterMode.DISABLED;

    // Current speed
    private double speed = 0;

    /**
     * Shooter constructor
     */
    public Shooter() {
        // Restore factory defaults
        this.shootMotor.restoreFactoryDefaults();

        // Set to coast mode
        this.shootMotor.setIdleMode(IdleMode.kCoast);

        // Current limit
        this.shootMotor.setSmartCurrentLimit(Constants.Shooter.CurrentLimit);

        // Add PID values to controller
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(0.0);
        pidController.setOutputRange(-1, 1);
    }

    /**
     * Turn on the shooter
     */
    public void enable() {
        this.currentMode = ShooterMode.ENABLED;
    }

    /**
     * Turn off the shooter
     */
    public void disable() {
        this.currentMode = ShooterMode.DISABLED;
    }

    /**
     * Set the shooter speed
     * @param speed wanted speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Shooter periodic
     */
    @Override
    public void periodic() {
        pidController.setReference(-speed, ControlType.kVelocity);

        pidController.setFF(Math.abs(speed*0.000000045));

        // Add the setpoint and actual to smart dashboard
        SmartDashboard.putNumber("SetPoint", speed);
        SmartDashboard.putNumber("Actual", encoder.getVelocity());
    }
}