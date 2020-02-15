package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
    // Create gyro
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    public Gyro() {
    } // nothing...

    @Override
    public void periodic() {
    } // and more nothing...

    // Expose gyro
    public AHRS getGyro() {
        return this.navx;
    }

    public double gyroYaw() {
        return navx.getYaw();
    }

    public double gyroPitch() {
        return navx.getPitch();
    }

    public double gyroRoll() {
        return navx.getRoll();
    }
}
