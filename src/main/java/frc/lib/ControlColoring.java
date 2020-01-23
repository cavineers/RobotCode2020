package frc.lib;

import frc.robot.subsystems.ColorSensor.ControlPanelColor;;

public class ControlColoring {
    public ControlPanelColor transposeToField(ControlPanelColor oldColor) {
        switch (oldColor) {
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

    public ControlPanelColor transposeFromField(ControlPanelColor oldColor) {
        switch (oldColor) {
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