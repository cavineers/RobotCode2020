package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.Limelight;
import frc.robot.Constants;

public class TurnTable extends SubsystemBase {
    // Motor
    public WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.TurnTable.MotorID); 

    // PID Controller
    private PIDController controller = new PIDController(Constants.TurnTable.kP, Constants.TurnTable.kI, Constants.TurnTable.kD);

    // TurnTable state
    public enum TurnTableState {
        ON,
        OFF
    }

    // Current state
    private TurnTableState currentState = TurnTableState.OFF;

    // Limelight for feedback
    private Limelight ll;

    // Homing switch
    private DigitalInput limitSwitch = new DigitalInput(Constants.TurnTable.LimitSwitch);

    /**
     * Constructor
     * @param ll limelight
     */
    public TurnTable(Limelight ll) {
        this.ll = ll;
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
        if (this.currentState == TurnTableState.ON) {
            this.tableMotor.pidWrite(this.controller.calculate(this.ll.getHorizontalOffset(), 0));
        } else {
            this.tableMotor.set(ControlMode.PercentOutput, 0);
        }
    }
}
