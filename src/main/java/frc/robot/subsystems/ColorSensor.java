package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {    
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

    // Arduino
    private Arduino arduino;

    /**
     * Constructor
     * @param arduino
     */
    public ColorSensor(Arduino arduino) {
        this.arduino = arduino;
    }

    /**
     * get the current color according to our robot
     * @return color the robot sees
     */
    public ControlPanelColor getColorAccordingToRobot() {
        // return the current color
        return this.currentColor;
    }

    /**
     * get the current color according to our field
     * @return transposed value from our robot
     */
    public ControlPanelColor getColorAccordingToField() {
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
        String r = this.arduino.receiveSerial();
        switch (r.substring(r.lastIndexOf("-") + 1)) {
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
    }
}
