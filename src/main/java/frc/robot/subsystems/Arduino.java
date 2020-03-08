package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arduino extends SubsystemBase {
    // Serial Port
    private SerialPort serialPort;

    /**
     * Arduino Constructor
     */
    public Arduino(SerialPort.Port port) {
        serialPort = new SerialPort(9600, port);
    }

    /**
     * Read serial data from the arduino
     * @return the serial string
     */
    public String receiveSerial() {
        // Read the string
        String word = serialPort.readString();

        // Reset the serial input
        serialPort.reset();

        // Return the final string
        return word;
    }

    /**
     * Arduino periodic
     */
    @Override
    public void periodic() {}
}