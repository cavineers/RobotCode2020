package frc.lib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class Limelight {
    private NetworkTable llTable;

    public Limelight() {
        this.llTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public NetworkTable getTable() {
        return this.llTable;
    }

    public double getHorizontalOffset() {
        return this.llTable.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffset() {
        return this.llTable.getEntry("ty").getDouble(0.0);
    }

    public double getRange() {
        return this.llTable.getEntry("ta").getDouble(0.0);
    }

    public double getScreenFill() {
        return this.llTable.getEntry("ta").getDouble(0.0);
    }

    public void setLightMode(int mode) {
        this.llTable.getEntry("ledMode").setNumber(mode);
    }

    public int getDistance() {
        double distance = (Constants.kFieldGoalHeightFromGround-Constants.kLimelightHeightFromGround)/Math.tan(Math.toRadians(Constants.kLimelightMountingAngle+NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0)));
        
        if (distance == Double.POSITIVE_INFINITY || distance == Double.NEGATIVE_INFINITY || distance < 0.0) {
            return 0;
        } else {
            return (int)Math.round(distance);
        }
        
    }

}