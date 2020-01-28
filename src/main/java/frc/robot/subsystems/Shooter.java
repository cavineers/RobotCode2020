package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.lib.Deadzone;
import frc.robot.Constants;

public class Shooter extends PIDSubsystem {

    // Fly wheel motor
    private CANSparkMax flyWheel = new CANSparkMax(Constants.kFlyWheelMotorCANid, MotorType.kBrushless);

    // Fly wheel PID
    private CANPIDController pidController = flyWheel.getPIDController();
    
    // Fly wheel encoder
    private CANEncoder flyingEncoder = flyWheel.getEncoder();

    // Set initial PID values
    public double kP = 0.0005;
    public double kI = 0.0;
    public double kD = 0.0001;
    public double kIz = 0.0;
    public double kMaxOutput = 1.0;
    public double kMinOutput = -1.0;
    public double maxRPM = 10000000;
    private double setPoint = -5000;
    public double kFF = 1/setPoint;
    //public double kFF = 0.01;
    
    //! TESTING JOYSTICK
    private Joystick joy;

    public Shooter(Joystick joy) {
        super(new PIDController(Constants.kShooterMotorPIDp, Constants.kShooterMotorPIDi, Constants.kShooterMotorPIDd));

        // Set the testing joystick
        this.joy = joy;

        // Configure the spark max
        this.flyWheel.setSmartCurrentLimit(Constants.kShooterCurrentLimit);

        // Add PID values to controller
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);

        // Add to smart dashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
    }

    @Override
    public void periodic() {
        super.periodic();
        // Get PID values from smart dashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
        if((p != kP)) { pidController.setP(p); kP = p; }
        if((i != kI)) { pidController.setI(i); kI = i; }
        if((d != kD)) { pidController.setD(d); kD = d; }
        if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
        if((ff != kFF)) { pidController.setFF(ff); kFF = ff; }
        if((max != kMaxOutput) || (min != kMinOutput)) { 
            pidController.setOutputRange(min, max); 
            kMinOutput = min; kMaxOutput = max; 
        }

        // Set the setpoint
        //double setPoint = -5000;
        pidController.setReference(setPoint, ControlType.kVelocity);

        // Add the setpoint and actual to smart dashboard
        SmartDashboard.putNumber("SetPoint", setPoint);
        SmartDashboard.putNumber("ProcessVariable", flyingEncoder.getVelocity());
    }

    @Override
    public void useOutput(double output, double setpoint) {}

    @Override
    public double getMeasurement() {
        return flyingEncoder.getPosition();
    }
}