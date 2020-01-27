package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
    private I2C.Port i2cPort = I2C.Port.kOnboard; // Setup i2c
    private ColorSensorV3 sensor = new ColorSensorV3(i2cPort); // Inst color sensor
    private ColorMatch colorMatch = new ColorMatch(); // setup color matcher
    
    // config colors
    private Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114); 
    private Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    // Current color
    private ControlPanelColor currentColor = ControlPanelColor.UNKNOWN;

    // Control Panel Controls
    public enum ControlPanelColor {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        UNKNOWN
    }

    public ColorSensor() {
        // Add colors to matcher
        colorMatch.addColorMatch(redTarget);
        colorMatch.addColorMatch(greenTarget);
        colorMatch.addColorMatch(blueTarget);
        colorMatch.addColorMatch(yellowTarget);    
    }

    @Override
    public void periodic() {
        Color detectedColor = sensor.getColor();
        ColorMatchResult result = colorMatch.matchClosestColor(detectedColor);

        if (result.color == redTarget) {
            this.currentColor = ControlPanelColor.RED;
        } else
        if (result.color == greenTarget) {
            this.currentColor = ControlPanelColor.GREEN;
        } else
        if (result.color == blueTarget) {
            this.currentColor = ControlPanelColor.BLUE;
        } else
        if (result.color == yellowTarget) {
            this.currentColor = ControlPanelColor.YELLOW;
        } else {
            this.currentColor = ControlPanelColor.UNKNOWN;
        }
    }

    public ControlPanelColor getRobotColor() {
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