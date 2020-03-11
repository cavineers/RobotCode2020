package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.Limelight;
import frc.robot.Constants;

public class TurnTable extends SubsystemBase {
    // Motor
    public WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.TurnTable.MotorID); 

    // PID Controller
    private PIDController pidController = new PIDController(Constants.TurnTable.kP, Constants.TurnTable.kI, Constants.TurnTable.kD);

    // TurnTable state
    public enum TurnTableState {
        TARGETING,
        OFF,
        NEUTRAL
    }

    // Current state
    private TurnTableState currentState = TurnTableState.OFF;

    // Limelight for feedback
    private Limelight ll;

    // Homing switch
    private DigitalInput limitSwitch = new DigitalInput(Constants.TurnTable.LimitSwitch);

    // At hard stop for dashboard
    public boolean atHardStop = false;

    // Last time for debug
    private double lastTime = 0;

    /**
     * Constructor
     * @param ll limelight
     */
    public TurnTable(Limelight ll) {
        this.ll = ll;

        // Configure the turntable soft stops
        this.tableMotor.configForwardSoftLimitThreshold(Constants.TurnTable.maxRight);
        this.tableMotor.configReverseSoftLimitThreshold(Constants.TurnTable.maxLeft);
        this.tableMotor.configForwardSoftLimitEnable(true);
        this.tableMotor.configReverseSoftLimitEnable(true);
    }

    /**
     * Set the ON/OFF state of the turntable tracker
     * @param state ON/OFF state
     */
    public void setState(TurnTableState state) {
        this.currentState = state;
    }

    /**
     * Get the current turntable state
     * @return
     */
    public TurnTableState getState() {
        return this.currentState;
    }

    /**
     * Get the limit switch value
     * @return is limit pressed
     */
    public boolean isLimitPressed() {
        return !this.limitSwitch.get();
    }

    /**
     * Periodic
     */
    @Override
    public void periodic() {
        if (Timer.getFPGATimestamp()-this.lastTime > 0.5) {
            // System.out.println(this.tableMotor.getSelectedSensorPosition());
            this.lastTime = Timer.getFPGATimestamp();
        }
        
        if (this.currentState == TurnTableState.TARGETING) {
            // Turn the turntable to meet a 0 horizontal degree offset from target
            this.tableMotor.pidWrite(this.pidController.calculate(-this.ll.getHorizontalOffset(), 0.0));
        } else
        if (this.currentState == TurnTableState.NEUTRAL) {
            // Turn the turntable to encoder position 0 (the original match starting position)
            this.tableMotor.pidWrite(this.pidController.calculate(this.tableMotor.getSelectedSensorPosition(), 0.0));
        }
    }
}
