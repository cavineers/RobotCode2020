package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


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

    /**
     * constructor
     * Defaults to retracted mode
     */
    public ControlPanel() {
        this.retract();
    }

    /**
     * Extend the piston
     */
    public void extend() {
        if (this.currentPos != ControlPanelPosition.EXTENDED) {
            controlSolenoid.set(Value.kForward);
            this.currentPos = ControlPanelPosition.EXTENDED;
        }
    }

    /**
     * Retract the piston
     */
    public void retract(){
        if (this.currentPos != ControlPanelPosition.RETRACTED) {
            controlSolenoid.set(Value.kReverse);
            this.currentPos = ControlPanelPosition.RETRACTED;
        }
    }

    /**
     * set the spinner speed
     * @param speed
     */
    public void setSpin(double speed) {
        System.out.println("set spin");
        this.controlMotor.set(speed);
    }

    /**
     * ControlPanel periodic
     */
    @Override
    public void periodic() {}
}