package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

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
    public double kIz = Constants.Shooter.PIDiz;
    
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
     * Shooter
     */
    public Shooter() {
        // Configure the spark max
        this.flyWheel.setSmartCurrentLimit(Constants.Shooter.ShooterCurrentLimit);

        // Add PID values to controller
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(1/Constants.Shooter.MaxRPM);
        pidController.setOutputRange(Constants.Shooter.MinOutput, Constants.Shooter.MaxOutput);

        // Add to smart dashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", 1/Constants.Shooter.MaxRPM);
        SmartDashboard.putNumber("Max Output", Constants.Shooter.MaxOutput);
        SmartDashboard.putNumber("Min Output", Constants.Shooter.MinOutput);
    }

    @Override
    public void periodic() {
        super.periodic();
        // Get PID values from smart dashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
        if((p != kP)) { pidController.setP(p); kP = p; }
        if((i != kI)) { pidController.setI(i); kI = i; }
        if((d != kD)) { pidController.setD(d); kD = d; }
        if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
        if((max != Constants.Shooter.MaxOutput) || (min != Constants.Shooter.MinOutput)) { 
            pidController.setOutputRange(min, max); 
            Constants.Shooter.MinOutput = min; Constants.Shooter.MaxOutput = max; 
        }

        if (this.currentMode == ShooterMode.ENABLED) {
            pidController.setReference(-Constants.Shooter.MaxRPM, ControlType.kVelocity);
            pidController.setReference(-speed, ControlType.kVelocity);
        } else {
            pidController.setReference(0, ControlType.kVelocity);
        }

        // Add the setpoint and actual to smart dashboard
        SmartDashboard.putNumber("SetPoint", Constants.Shooter.MaxRPM);
        SmartDashboard.putNumber("ProcessVariable", flyingEncoder.getVelocity());
    }

    public void enable() {
        this.currentMode = ShooterMode.ENABLED;
    }

    public void disable() {
        this.currentMode = ShooterMode.DISABLED;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}