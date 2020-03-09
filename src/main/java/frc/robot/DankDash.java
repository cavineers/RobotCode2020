package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class DankDash {
    private NetworkTable netTable;
    private String profileName;
    private String profileLocation;

    private double lastTime = 0;

    private int heartbeatValue = 0;

    public DankDash() {
        this.netTable = NetworkTableInstance.getDefault().getTable("DankDash");
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void addListener() {
        netTable.addEntryListener((table, key, entry, value, flags) -> {
            System.out.println("Key: " + key);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

    public String getProfileName() {
        return this.profileName;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }

    public String getProfileLocation() {
        return this.profileLocation;
    }

    public void export() {
        netTable.getEntry("ProfileData").setString(this.profileLocation);
        netTable.getEntry("ProfileName").setString(this.profileName);
    }

    public void sendDash(String id, String data) {
        netTable.getEntry(id).setString(data);
    }

    public void dankPeriodic() {
        if (Timer.getFPGATimestamp()-this.lastTime > 1) { // update the dashboard display once per second
            this.heartbeatValue++;
            this.sendDash("Heartbeat", Double.toString(heartbeatValue));
            this.sendDash("MatchTime", Double.toString(DriverStation.getInstance().getMatchTime()));
            this.lastTime = Timer.getFPGATimestamp();
        }
    }
}