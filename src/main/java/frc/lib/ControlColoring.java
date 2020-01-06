package frc.lib;

public class ControlColoring {
    public enum ControlColor {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        ERROR
    }

    public ControlColor transposeToField(ControlColor oldColor) {
        switch (oldColor) {
            case RED:
                return ControlColor.BLUE;
            case GREEN:
                return ControlColor.YELLOW;
            case BLUE:
                return ControlColor.RED;
            case YELLOW:
                return ControlColor.GREEN;
            default:
                return ControlColor.ERROR;
        }
    }

    public ControlColor transposeFromField(ControlColor oldColor) {
        switch (oldColor) {
            case RED:
                return ControlColor.BLUE;
            case GREEN:
                return ControlColor.YELLOW;
            case BLUE:
                return ControlColor.RED;
            case YELLOW:
                return ControlColor.GREEN;
            default:
                return ControlColor.ERROR;
        }
    }
}