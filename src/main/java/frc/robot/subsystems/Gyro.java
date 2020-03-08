package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
    // Create gyro
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    /**
     * Constructor
     */
    public Gyro() {}

    // Expose gyro
    public AHRS getGyro() {
        return this.navx;
    }

    /**
     * Gyro periodic
     */
    @Override
    public void periodic() {}
}
