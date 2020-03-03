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
    private CANSparkMax flyWheel = new CANSparkMax(Constants.Shooter.ShootID, MotorType.kBrushless);

    // Fly wheel PID
    private CANPIDController pidController = flyWheel.getPIDController();
    
    // Fly wheel encoder
    private CANEncoder flyingEncoder = flyWheel.getEncoder();

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
        this.flyWheel.restoreFactoryDefaults();

        // Set to coast mode
        this.flyWheel.setIdleMode(IdleMode.kCoast);

        // Current limit
        this.flyWheel.setSmartCurrentLimit(Constants.Shooter.CurrentLimit);

        // Add PID values to controller
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(0.0);
        pidController.setFF(0.02);
        pidController.setOutputRange(-1, 1);

        // Add to smart dashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
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
        // Get PID values from smart dashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        if((p != kP)) { pidController.setP(p); kP = p; }
        if((i != kI)) { pidController.setI(i); kI = i; }
        if((d != kD)) { pidController.setD(d); kD = d; }

        pidController.setReference(-speed, ControlType.kCurrent);

        pidController.setFF(0.97/speed);

        // Add the setpoint and actual to smart dashboard
        SmartDashboard.putNumber("SetPoint", speed);
        SmartDashboard.putNumber("Actual", flyingEncoder.getVelocity());
    }
}