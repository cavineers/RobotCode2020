package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class ControlPanel extends SubsystemBase {
    // Control Panel Solenoid
    private DoubleSolenoid controlSolenoid = new DoubleSolenoid(Constants.ControlPanel.PCMChannel1, Constants.ControlPanel.PCMChannel2);
    private WPI_TalonSRX controlMotor = new WPI_TalonSRX(Constants.ControlPanel.MotorID);
    
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
            controlSolenoid.set(Value.kForward);
            this.currentPos = ControlPanelPosition.EXTENDED;
        }
    }

    public void retract(){
        if (this.currentPos != ControlPanelPosition.RETRACTED) {
            controlSolenoid.set(Value.kReverse);
            this.currentPos = ControlPanelPosition.RETRACTED;
        }
    }

    public void setSpin(double speed) {
        this.controlMotor.set(speed);
    }

    @Override
    public void periodic() {}
}