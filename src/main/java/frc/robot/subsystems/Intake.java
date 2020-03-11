package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    // Intake motor state
    public enum IntakeMotorState {
        ON,
        OFF,
        REVERSED,
        OOF
    }

    // Intake piston state
    public enum IntakePistonState {
        EXTENDED,
        RETRACTED
    }
    
    // Motor
    private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Intake.MotorID);

    // Solenoid
    private DoubleSolenoid sol = new DoubleSolenoid(Constants.Intake.Piston1, Constants.Intake.Piston2);

    // Current states
    private IntakeMotorState currentMotorState;

    private IntakePistonState currentPistonState;

    // Robot Container
    private RobotContainer rc;

    private double lastTime = 0.0;

    private double time;

    // Constructor
    public Intake(RobotContainer rc) {
        // local robot container
        this.rc = rc;
        
        // Config motor
        this.motor.setNeutralMode(NeutralMode.Brake);

        // Default to off and retracted
        this.setMotorState(IntakeMotorState.OFF);
        this.setPistonState(IntakePistonState.RETRACTED);

    }

    /**
     * set the desired intake state
     * @param state wanted intake state
     */
    public void setMotorState(IntakeMotorState state) {
        // set the current state
        this.currentMotorState = state;

        // set motor state
        switch (state) {
            case ON:
                // On
                this.motor.set(ControlMode.PercentOutput, Constants.Intake.InSpeed);
                break;
            case OFF:
                // Off
                this.motor.set(ControlMode.PercentOutput, 0);
                break;
            case REVERSED:
                // Reversed
                this.motor.set(ControlMode.PercentOutput, Constants.Intake.OutSpeed);
                break;
            case OOF:
                this.motor.set(-1);
                break;
        }
    }

    /**
     * get the current intake state
     * @return intake state
     */
    public IntakeMotorState getMotorState() {
        // return the current motor state
        return this.currentMotorState;
    }

    /**
     * Set the wanted piston state
     * @param state extended/reversed
     */
    public void setPistonState(IntakePistonState state) {
        switch (state) {
            case EXTENDED:
                this.sol.set(DoubleSolenoid.Value.kForward);
                break;
            case RETRACTED:
                this.sol.set(DoubleSolenoid.Value.kReverse);
                this.setMotorState(IntakeMotorState.OFF);
                break;
            default:
                break;
        }
    }

    /**
     * Get the current piston state
     * @return extended/reversed
     */
    public IntakePistonState getPistonState() {
        return this.currentPistonState;
    }


    /**
     * Intake periodic
     */
    @Override
    public void periodic() {
        if (Timer.getFPGATimestamp()-this.lastTime>0.75) {
            // System.out.println("IR:" + this.getIRVal());
            this.lastTime = Timer.getFPGATimestamp();
        }

        // Current limiting
        if (this.rc.PDP.getCurrent(Constants.PDPPorts.IntakeMotor) > 18) {
            this.setMotorState(IntakeMotorState.REVERSED);
            this.time = Timer.getFPGATimestamp();
            this.rc.drum.addBall();
        }

        // Turn off after reverse
        if (this.time != 0 && Timer.getFPGATimestamp()-this.time > 1) {
            this.setMotorState(IntakeMotorState.OFF);
            this.time = 0;
        }
    }
}
