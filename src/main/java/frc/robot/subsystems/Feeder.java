package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    // Feeder state
    public enum FeederState {
        ENABLED,
        DISABLED
    }

    // Motor
    private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Feeder.MotorID);

    // Current feeder state
    private FeederState currentState = FeederState.DISABLED;

    public Feeder() {
        this.setState(FeederState.DISABLED);
    }

    @Override
    public void periodic() {}

    public void setState(FeederState state) {
        switch (state) {
            case ENABLED:
                this.motor.set(ControlMode.PercentOutput, Constants.Feeder.Speed);
                break;
            case DISABLED:
                this.motor.set(ControlMode.PercentOutput, 0);
                break;
        }
        this.currentState = state;
    }

    public FeederState getState() {
        return this.currentState;
    }
}
