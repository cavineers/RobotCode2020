package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
    // Create the color sensor usb controller to go between the arduino and rio
    private SerialPort usbController = new SerialPort(9600, SerialPort.Port.kUSB1);
    
    // Control Panel Controls
    public enum ControlPanelColor {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        UNKNOWN
    }

    // Current color
    private ControlPanelColor currentColor = ControlPanelColor.UNKNOWN;

    public ColorSensor() {}

    /**
     * read the serial string
     * @return serial string
     */
    public String readSerial() {
        // get string
        String r = this.usbController.readString();
        // return the serial string
        return r.substring(r.lastIndexOf("-") + 1);
    }

    /**
     * reset the serial data
     */
    public void resetSerial() {
        // reset the serial string
        this.usbController.reset();
    }

    /**
     * get the current color according to our robot
     * @return color the robot sees
     */
    public ControlPanelColor getColor() {
        // return the current color
        return this.currentColor;
    }

    /**
     * get the field color
     * @return transposed value from our robot
     */
    public ControlPanelColor getFieldColor() {
        // get the field color through transpose
        return this.transpose(this.currentColor);
    }

    /**
     * transpose between our color sensor and the fields
     * @param panelColor color before
     * @return color after
     */
    public ControlPanelColor transpose(ControlPanelColor panelColor) {
        // quickly transpose
        switch (panelColor) {
            case RED:
                return ControlPanelColor.BLUE;
            case GREEN:
                return ControlPanelColor.YELLOW;
            case BLUE:
                return ControlPanelColor.RED;
            case YELLOW:
                return ControlPanelColor.GREEN;
            default:
                return ControlPanelColor.UNKNOWN;
        }
    }

    /**
     * ColorSensor periodic
     */
    public void periodic() {
        switch (this.readSerial()) {
            case "R":
            case "r":
                // red
                this.currentColor = ControlPanelColor.RED;
                break;
            case "G":
            case "g":
                // green
                this.currentColor = ControlPanelColor.GREEN;
                break;
            case "B":
            case "b":
                // blue
                this.currentColor = ControlPanelColor.BLUE;
                break;
            case "Y":
            case "y":
                // yellow
                this.currentColor = ControlPanelColor.YELLOW;
                break;
            case "unknown":
            case "Unknown":
            case "null":
            case "Null":
            case "N":
            case "n":
            case "U":
            case "u":
                // unknown or null
                this.currentColor = ControlPanelColor.UNKNOWN;
                break;
            default:
                // whoops...
                this.currentColor = ControlPanelColor.UNKNOWN;
                break;
        }
        // reset serial string
        this.resetSerial();
    }
}
