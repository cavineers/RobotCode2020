package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class DankDash {
    // Network tables
    private NetworkTable netTable;

    // Robot name
    private String robotName;

    // Profile name
    private String profileName;

    // Time updates
    private double lastTime = 0;

    // Heart beat
    private int heartbeatValue = 0;

    /**
     * constructor
     */
    public DankDash() {
        // Setup NT
        this.netTable = NetworkTableInstance.getDefault().getTable("DankDash");
    }

    /**
     * Set the robot's name
     * @param name robot name
     * @return self
     */
    public DankDash setRobotName(String name) {
        this.robotName = name;
        return this;
    }

    /**
     * Get the robot's name
     * @return robot name
     */
    public String getRobotName() {
        return this.robotName;
    }

    /**
     * Set the profile name
     * @param name profile name
     * @return self
     */
    public DankDash setProfileName(String name) {
        this.profileName = name;
        return this;
    }

    /**
     * Get the profile name
     * @return profile name
     */
    public String getProfileName() {
        return this.profileName;
    }

    /**
     * Export to dank
     * @return self
     */
    public DankDash export() {
        netTable.getEntry("RobotName").setString(this.robotName);
        netTable.getEntry("ProfileName").setString(this.profileName);
        return this;
    }

    /**
     * Send data to dank dash
     * @param id Data ID
     * @param data Data string
     * @return self
     */
    public DankDash sendDash(String id, String data) {
        netTable.getEntry(id).setString(data);
        return this;
    }

    /**
     * Add event listeners to keys
     */
    public void addListener() {
        netTable.addEntryListener((table, key, entry, value, flags) -> {
            // System.out.println("Key: " + key);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

    /**
     * Periodic
     */
    public void dankPeriodic() {
        if (Timer.getFPGATimestamp()-this.lastTime > 1) { // update the dashboard display once per second
            this.heartbeatValue++;
            this.sendDash("Heartbeat", Double.toString(heartbeatValue));
            this.sendDash("MatchTime", Double.toString(DriverStation.getInstance().getMatchTime()));
            this.lastTime = Timer.getFPGATimestamp();
        }
    }
}