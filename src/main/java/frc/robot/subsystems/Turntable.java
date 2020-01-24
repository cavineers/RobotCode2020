package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Turntable extends SubsystemBase {
    private WPI_TalonSRX tableMoter = new WPI_TalonSRX(Constants.kTurretRotationMotorCANid);

    public enum TurntableMode {
        ROTATE_LEFT,
        ROTATE_RIGHT,
        STOPPED,
        HOMING
    }

    private TurntableMode currentMode;

    public Turntable() {
        tableMoter.configPeakCurrentLimit(2, 10);
        tableMoter.configPeakCurrentDuration(200, 10);
        tableMoter.configContinuousCurrentLimit(1, 10);
        tableMoter.enableCurrentLimit(true);

        this.currentMode = TurntableMode.STOPPED;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setMotorRotation(TurntableMode mode) {
        if (this.currentMode == TurntableMode.HOMING) return; // Don't touch anything while home
        switch (mode) {
            case ROTATE_LEFT:
                tableMoter.set(-Constants.kTurntableSpeed);
                break;
            case ROTATE_RIGHT:
                tableMoter.set(Constants.kTurntableSpeed);
                break;
            case STOPPED:
                tableMoter.set(0.0);
                break;
            default:
                break;
        }
    }
}
