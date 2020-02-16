package frc.lib;

import edu.wpi.first.networktables.NetworkTable;
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

    private double llCatch(double a) {
        if (a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY || a < 0.0) {
            return 0.0;
        } else {
            return a;
        }
    }

    public int getDistance() {
        double height1 = Constants.Vision.kLimelightHeightFromGround;
        double height2 = Constants.Vision.kFieldGoalHeightFromGround;
        double angle1 = Constants.Vision.kLimelightMountingAngle;
        double angle2 = this.llTable.getEntry("ty").getDouble(0.0);
        double distance = (height2-height1) * (1 / Math.tan(Math.toRadians(angle1+angle2)));
        return (int)Math.round(this.llCatch(distance));
    }
}