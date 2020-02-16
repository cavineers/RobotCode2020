package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.SerialPort;

public class ColorSensor extends SubsystemBase {
    // Create the color sensor usb controller to go between the arduino and rio
    // private SerialPort usbController = new SerialPort(9600, SerialPort.Port.kUSB1);
    
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

    @Override
    public void periodic() {
        // Read the string that's given from the arduino
        // String dataBack = usbController.readString();
        
        // Print the data given by the controller
        // Robot.logger.logln(dataBack);

        // Reset the input
        // usbController.reset(); //? maybe, untested
    }

    public ControlPanelColor getColor() {
        return this.currentColor;
    }

    public ControlPanelColor getFieldColor() {
        return this.transpose(this.currentColor);
    }

    public ControlPanelColor transpose(ControlPanelColor panelColor) {
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
}
