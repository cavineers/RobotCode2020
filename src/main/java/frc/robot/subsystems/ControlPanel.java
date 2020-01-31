package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {
    // Control Panel Solenoid
    private Solenoid controlSolenoid = new Solenoid(Constants.ControlPanel.PCMChannel1);
    
    // Control Panel Position
    public enum ControlPanelPosition {
        EXTENDED,
        RETRACTED,
        UNKNOWN
    }

    // Current position
    private ControlPanelPosition currentPos = ControlPanelPosition.UNKNOWN;

    public ControlPanel() {
        this.retract();
    }

    public void extend() {
        if (this.currentPos != ControlPanelPosition.EXTENDED) {
            controlSolenoid.set(true);
            this.currentPos = ControlPanelPosition.EXTENDED;
        }
    }

    public void retract(){
        if (this.currentPos != ControlPanelPosition.RETRACTED) {
            controlSolenoid.set(false);
            this.currentPos = ControlPanelPosition.RETRACTED;
        }
    }

    @Override
    public void periodic() {}
}