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

    public double getDistance() {
        // System.out.println("Height from ground: " + Constants.kFieldGoalHeightFromGround);
        // System.out.println("Limelight from ground: " + Constants.kLimelightHeightFromGround);
        // System.out.println("Mounting Angle: " + Constants.kLimelightMountingAngle);
        // System.out.println("ty: " + this.llTable.getEntry("ty").getDouble(0));

        // (height of target - height of camera above floor) / tan (mounting angle + y angle to target)
        double tmp = (Constants.kFieldGoalHeightFromGround-Constants.kLimelightHeightFromGround)/Math.tan(Math.toRadians(Constants.kLimelightMountingAngle+NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0)));
        
        if (tmp == Double.POSITIVE_INFINITY || tmp == Double.NEGATIVE_INFINITY || tmp < 0) {
            return 0.0;
        } else {
            // if (tmp < 50) {
            //     return tmp;
            // } else
            // if (tmp < 70) {
            //     return tmp-5;
            // } else
            // if (tmp < 100) {
            //     return tmp-10;
            // } else
            // if (tmp < 150) {
            //     return tmp-15;
            // } else
            // if (tmp < 200) {
            //     return tmp-20;
            // } else {
            //     return tmp;
            // }
            return tmp;
        }

        // return (h/Math.tan(Math.toRadians(this.llTable.getEntry("ty").getDouble(0.0))));
        // return 0.0;
        
    }

}