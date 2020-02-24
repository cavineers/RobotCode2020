package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    // Intake state
    public enum IntakeState {
        ON,
        OFF,
        REVERSED
    }
    
    // Motor
    private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Intake.MotorID);

    // Current state
    private IntakeState currentState;

    // Robot Container
    private RobotContainer rc;

    // Timing
    private double reverseTime = 0;
    private double overdrawTime = 0;

    // Constructor
    public Intake(RobotContainer rc) {
        // local robot container
        this.rc = rc;
        
        // Default to off
        this.setState(IntakeState.OFF);
    }

    @Override
    public void periodic() {
        // // If over draw limit..
        // if (this.rc.getCurrentDrawOfPort(Constants.PDPPorts.IntakeMotor) > Constants.Intake.MaxCurrentDraw) {
        //     // if the draw just began, set time
        //     if (this.overdrawTime == 0) {
        //         this.overdrawTime = Timer.getFPGATimestamp();
        //     } else
        //     if (Timer.getFPGATimestamp()-this.overdrawTime > Constants.Intake.MaxDrawTime) {
        //         // if over the max current draw time, reverse the rollers
        //         this.setState(IntakeState.REVERSED);
        //     }
        // } else
        // if (this.reverseTime != 0) {
        //     // if it's in the reverse state
        //     if (Timer.getFPGATimestamp()-this.reverseTime > Constants.Intake.ReverseTime) {
        //         // and the time is done, turn off the motor
        //         this.setState(IntakeState.OFF);
        //     }
        // } else {
        //     // default to no overdraw
        //     this.overdrawTime = 0;
        // }
    }

    /**
     * set the desired intake state
     * @param state wanted intake state
     */
    public void setState(IntakeState state) {
        // set the current state
        this.currentState = state;

        // set motor state
        switch (state) {
            case ON:
                // On
                this.motor.set(ControlMode.PercentOutput, Constants.Intake.InSpeed);
                this.reverseTime = 0;
                this.overdrawTime = 0;
                break;
            case OFF:
                // Off
                this.motor.set(ControlMode.PercentOutput, 0);
                this.reverseTime = 0;
                this.overdrawTime = 0;
                break;
            case REVERSED:
                // Reversed
                this.motor.set(ControlMode.PercentOutput, Constants.Intake.OutSpeed);
                this.reverseTime = Timer.getFPGATimestamp();
                this.overdrawTime = 0;
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
}
