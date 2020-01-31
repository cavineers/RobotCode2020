package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turntable extends SubsystemBase {
    private WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.Turntable.MotorID);

    public enum TurntableMode {
        ROTATE_LEFT,
        ROTATE_RIGHT,
        STOPPED,
        HOMING
    }

    private TurntableMode currentMode;

    public Turntable() {
        tableMotor.setSelectedSensorPosition(0);

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
                tableMotor.set(-Constants.Turntable.speed);
                break;
            case ROTATE_RIGHT:
                tableMotor.set(Constants.Turntable.speed);
                break;
            case STOPPED:
                tableMotor.set(0.0);
                break;
            default:
                break;
        }
    }

    public int getTurntableDegree() {
        // return (this.tableMotor.getSelectedSensorPosition()/4096);
        return 0;
    }
}
