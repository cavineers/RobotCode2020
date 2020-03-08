package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    // Intake state
    public enum IntakeState {
        ON,
        OFF,
        REVERSED,
        OOF
    }
    
    // Motor
    private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Intake.MotorID);

    // Current state
    private IntakeState currentState;

    // Robot Container
    private RobotContainer rc;

    private double lastTime = 0.0;

    // Turn back on
    private boolean turnBackOn = false;

    private int onStage = 0;

    private double turnDelay = 0;

    private double onDelay = 0;

    // IR Sensor
    private AnalogInput ir = new AnalogInput(Constants.Drum.IRSensor);

    private double time;

    // Constructor
    public Intake(RobotContainer rc) {
        // local robot container
        this.rc = rc;
        
        // Default to off
        this.setState(IntakeState.OFF);

        this.motor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * set the desired intake state
     * @param state wanted intake state
     */
    public void setState(IntakeState state) {
        // System.out.println("intake");
        // set the current state
        this.currentState = state;
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
    public IntakeState getState() {
        // return the current motor state
        return this.currentState;
    }

    public double getIRVal() {
        return ((27.726)*(Math.pow(this.ir.getVoltage(), -1.2045)));
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

        // if (this.turnBackOn) {
        //     switch (this.onStage) {
        //         case 1:
        //             if (this.rc.drum.isTurning()) {
        //                 this.onStage++;
        //             } else {
        //                 this.setState(IntakeState.OFF);
        //             }
        //             break;
        //         case 2:
        //             if (!this.rc.drum.isTurning()) {
        //                 this.onStage++;
        //                 this.onDelay = Timer.getFPGATimestamp();
        //             } else {
        //                 this.setState(IntakeState.OFF);
        //             }
        //         case 3:
        //             if (Timer.getFPGATimestamp()-this.onDelay > 5.0) {
        //                 // this.setState(IntakeState.ON);
        //                 this.onStage = 0;
        //                 this.onDelay = 0;
        //             }
        //         default:
        //             break;
        //     }
        // }

        // if (this.getIRVal() <= Constants.Intake.BallDetectionVoltage && this.currentState == IntakeState.ON) {
        //     System.out.println("off");
        //     this.setState(IntakeState.OFF);
        //     this.rc.drum.addBall();
        //     if (this.rc.drum.getBallCount() != 5) {
        //         this.turnDelay = Timer.getFPGATimestamp();
        //         this.turnBackOn = true;
        //         this.onStage++;
        //     }
        // }

        if (Timer.getFPGATimestamp()-this.turnDelay > 2.0 && this.turnDelay != 0) {
            this.rc.drum.moveToNext();
            this.turnDelay = 0;
            this.time = 0;
        }

        // System.out.println(this.rc.PDP.getCurrent(Constants.PDPPorts.IntakeMotor));

        if (this.rc.PDP.getCurrent(Constants.PDPPorts.IntakeMotor) > 18) {
            this.rc.drum.enable();
            this.setState(IntakeState.REVERSED);
            this.time = Timer.getFPGATimestamp();
            this.rc.drum.addBall();
            this.turnDelay = 0;
            if (this.rc.drum.getBallCount() < 5 && !this.rc.isDrumManual()) {
                this.turnDelay = Timer.getFPGATimestamp();
                this.turnBackOn = true;
                this.onStage++;
            }
        }

        if (time != 0 && Timer.getFPGATimestamp()-this.time > 1) {
            this.setState(IntakeState.OFF);
            this.time = 0;
        }
    }
}
