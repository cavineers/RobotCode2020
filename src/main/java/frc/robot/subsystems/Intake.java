
package frc.robot.subsystems;

import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase {

    public TalonSRX ballIntakeMotor = new TalonSRX(Constants.kIntakeMotorCANid);

    public boolean isOn = false;
    public boolean drumON = false;
    public boolean drumOnT = false;

    public float drumPosition = 0;
    public int numberStopper = 0;
    public double intakeVoltage = 0;

    public Intake() {

        this.getIntakeMotor().setNeutralMode(NeutralMode.Brake);

    }

    @Override

    public void periodic() {

        intakeVoltage = this.getIntakeMotor().getMotorOutputVoltage();

        // if the voltage gets to high it means it is jammed
        if (intakeVoltage > 60) {
            isOn = false;
            ballIntakeMotor.set(ControlMode.PercentOutput, -2);
        }

        // this if statement is used to turn on the intake
        else if (isOn == true) {
            ballIntakeMotor.set(ControlMode.PercentOutput, .1);
        }

        else if (isOn == false) {

            ballIntakeMotor.set(ControlMode.PercentOutput, 0);

        }

        else {

            ballIntakeMotor.set(ControlMode.PercentOutput, 0);

        }

    }

    public TalonSRX getIntakeMotor() {

        return this.ballIntakeMotor;

    }

    public void turnOn() {
        isOn = true;
    }

}
