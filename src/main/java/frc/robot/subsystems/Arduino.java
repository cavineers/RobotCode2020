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
     * Enables LED on ColorSensor - 
     * Don't trigger more than once.
     */
    public void enableLED() {
        String State = "ON";
        serialPort.writeString(State);
    }

    /**
     * Disables LED on ColorSensor - 
     * Don't trigger more than once.
     */
    public void disableLED() {
        String State = "OFF";
        serialPort.writeString(State);
        serialPort.reset();
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
    public String getColor(){
        // Read the string
        String word = serialPort.readString();

        // Reset the serial input
        serialPort.reset();

        // Return the final string
        return word;
    }
    public boolean detectsColor(){
        String word = serialPort.readString();
        serialPort.reset();
        if(word == "r" || word == "g" || word == "y" || word == "b" ){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Arduino periodic
     */
    @Override
    public void periodic() {}
}