/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arduino extends SubsystemBase {
  /**
   * Creates a new Arduino.
   */
  private SerialPort serialPort;

<<<<<<< Updated upstream
  public Arduino() {
    serialPort = new SerialPort(9600, SerialPort.Port.kUSB1);
  }
=======
    /**
     * Read serial data from the arduino
     * @return the serial string
     */

    public void enableLED() {
        String State = "ON";
        serialPort.writeString(State);
        serialPort.reset();
    }

    public void disableLED() {
        String State = "OFF";
        serialPort.writeString(State);
        serialPort.reset();
    }

    public String receiveSerial() {
        // Read the string
        String word = serialPort.readString();
>>>>>>> Stashed changes

  public String receiveSerial() {
    String Word = serialPort.readString();
    serialPort.reset();
    return Word;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(serialPort.readString());
  }
}
