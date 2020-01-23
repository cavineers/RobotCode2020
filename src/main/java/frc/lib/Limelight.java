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

    private double llCatch(double a) {
        if (a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY || a < 0.0) {
            return 0.0;
        } else {
            return a;
        }
    }

    public int getDistance() {
        double distance = (Constants.kFieldGoalHeightFromGround-Constants.kLimelightHeightFromGround)/Math.tan(Math.toRadians(Constants.kLimelightMountingAngle+NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0)));
        return (int)Math.round(this.llCatch(distance));
    }

    public int getDistance2() {
        //$ Tuning
        // 1. align limelight so it is facing a target directly straight
        // 2. get ta from limelight webpage (10.1.17.1:5801) under image
        // 3. measure distance with a tape measure
        // 4. update variables
        //      a1 <-> ta from ll webpage (originally 1.203)
        //      d1 <-> actual distance measured (originally 64)

        double tv = this.llTable.getEntry("tv").getDouble(0);
        double tx = this.llTable.getEntry("tx").getDouble(0);
        double ty = this.llTable.getEntry("ty").getDouble(0);
        double ta = this.llTable.getEntry("ta").getDouble(0);

        if (tv < 1.0) {
            return 0;
        } else {
            double d1 = 82.0; // Actual Distance
            double a1 = 1.900; // Matching ta
            double distraw = Math.sqrt((a1*Math.pow(d1,2))/ta);
            double tdist = Math.cos(ty)*distraw;

            return (int)Math.round(this.llCatch(tdist));
        }
    }

    public int getDistance3() {
        double height1 = Constants.kLimelightHeightFromGround;
        double height2 = Constants.kFieldGoalHeightFromGround;
        double angle1 = Constants.kLimelightMountingAngle;
        double angle2 = this.llTable.getEntry("ty").getDouble(0.0);
        double distance = (height2-height1) * (1 / Math.tan(Math.toRadians(angle1+angle2)));
        int re = (int)Math.round(this.llCatch(distance));
        // System.out.println(re);

        if (re > 210) {
            return re+10;
        } else {
            return re;
        }
    }
}