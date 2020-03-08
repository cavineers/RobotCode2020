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
        pidController.setIZone(0.0);
        pidController.setOutputRange(-1, 1);

        SmartDashboard.putNumber("shooter_p", Constants.Shooter.PIDp);
        SmartDashboard.putNumber("shooter_i", Constants.Shooter.PIDi);
        SmartDashboard.putNumber("shooter_d", Constants.Shooter.PIDd);
        SmartDashboard.putNumber("shooter_f", Math.abs(0.000182));
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

    public ShooterMode getCurrentMode() {
        return this.currentMode;
    }

    /**
     * Set the shooter speed
     * @param speed wanted speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Is the speed at the setpoint
     * @return at setpoint
     */
    public boolean closeEnough() {
        return (Math.abs(this.speed-Math.abs(this.encoder.getVelocity()))<120);
    }

    /**
     * Shooter periodic
     */
    @Override
    public void periodic() {
        pidController.setReference(-this.speed, ControlType.kVelocity);

        if (this.currentMode == ShooterMode.ENABLED && this.speed != 0) {
            // pidController.setP(Constants.Shooter.PIDp);
            // pidController.setI(Constants.Shooter.PIDi);
            // pidController.setD(Constants.Shooter.PIDd);
            // pidController.setFF(Math.abs(0.000182));
            pidController.setP(SmartDashboard.getNumber("shooter_p", 0));
            pidController.setI(SmartDashboard.getNumber("shooter_i", 0));
            pidController.setD(SmartDashboard.getNumber("shooter_d", 0));
            pidController.setFF(Math.abs(SmartDashboard.getNumber("shooter_f", 0)));
        } else {
            pidController.setP(0);
            pidController.setP(0);
            pidController.setP(0);
            pidController.setFF(0);
        }

        // System.out.println(this.encoder.getVelocity());

        // Add the setpoint and actual to smart dashboard
        SmartDashboard.putNumber("SetPoint", this.speed);
        SmartDashboard.putNumber("Actual", this.encoder.getVelocity());
    }
}