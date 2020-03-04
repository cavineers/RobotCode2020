package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drum extends SubsystemBase {
    public TalonSRX rotatingDrumMotor = new TalonSRX(Constants.CANIds.DrumMotor);
    DigitalInput limitSwitch;

    AnalogInput irSensor;
    public boolean finishedShooting = false;
    public boolean readyToShoot = false;
    public boolean drumReady = false;
    public double numberStopper = 0;
    public double numberOfBalls = 0;
    public double numberStopperIR = 0;
    public double drumPosition = 1;
    public double counter = 0;
    public double slotNumber = 1;

    public enum DrumPosition {
        HOLE1, HOLE2, HOLE3, HOLE4, HOLE5, FULL, INVALID;
    }

    public Drum() {
        limitSwitch = new DigitalInput(Constants.Drum.LimitSwitch);

        irSensor = new AnalogInput(Constants.Drum.IrSensor);
        // set the neutral mode to brake
        this.getDrumMotor().setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        if (rotatingDrumMotor.getBusVoltage() > 20) {
            rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
        } else if (drumReady == true) {
            // when the robot inits or after the robot is ready to shoot the variable should
            // be false to stop it from stalling when shooting
        } else {
            // System.out.println("Drum not ready");
        }
        if (drumReady == false) {
            final long startTime = System.currentTimeMillis();
            if (irSensor.getVoltage() > Constants.Drum.IrSensorTrigger) {
                if (numberStopperIR == 0) {

                    while (startTime + 500 > System.currentTimeMillis()) {

                    }
                    numberOfBalls = numberOfBalls + 1;
                    numberStopperIR = 1;
                }

            }
        }
        if (irSensor.getVoltage() < Constants.Drum.IrSensorTrigger) {
            numberStopperIR = 0;
        }

        System.out.println(numberOfBalls);
        // System.out.println(irSensor.getVoltage());
        if (irSensor.getVoltage() > Constants.Drum.IrSensorTrigger) {
            System.out.println("true");
        } else if (irSensor.getVoltage() < Constants.Drum.IrSensorTrigger) {
            System.out.println("false");
        } else {
            System.out.println("error");
        }

        if (numberOfBalls > 4 & irSensor.getVoltage() < Constants.Drum.IrSensorTrigger) {
            numberOfBalls = 0;
        }

    }

    // Get the drum motor
    public TalonSRX getDrumMotor() {

        return this.rotatingDrumMotor;

    }

    // Get drum motor position
    public double getPosition() {
        return rotatingDrumMotor.getSelectedSensorPosition();
    }

    // get limit switch value
    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }

    // find the open drum hole
    public DrumPosition getDesiredPosition() {
        if (numberOfBalls == 0) {
            return DrumPosition.HOLE1;
        } else if (numberOfBalls == 1) {
            return DrumPosition.HOLE2;
        } else if (numberOfBalls == 2) {
            return DrumPosition.HOLE3;
        } else if (numberOfBalls == 3) {
            return DrumPosition.HOLE4;
        } else if (numberOfBalls == 4) {
            return DrumPosition.HOLE5;
        } else if (numberOfBalls == 5) {
            return DrumPosition.FULL;
        } else {
            return DrumPosition.INVALID;
        }

    }

    // go tp the desired` drum hole
    public void goToDesiredPosition(DrumPosition position) {
        switch (position) {
        case HOLE1:
            nextPosition();
            slotNumber = 1;
            break;
        case HOLE2:
            nextPosition();
            slotNumber = 2;
            break;
        case HOLE3:
            nextPosition();
            slotNumber = 3;
            break;

        case HOLE4:
            nextPosition();
            slotNumber = 4;
            break;
        case HOLE5:
            nextPosition();
            slotNumber = 5;
            break;
        case FULL:
            readyToShoot = true;
            drumReady = false;
            break;
        case INVALID:
            System.out.println("ERROR");
            break;

        }

    }

    // go to next position
    public void nextPosition() {
        rotatingDrumMotor.setSelectedSensorPosition(0);
        while (rotatingDrumMotor.getSelectedSensorPosition() < 200) {
            rotatingDrumMotor.set(ControlMode.PercentOutput, .25);
        }
        while (getLimitSwitch() == false) {
            rotatingDrumMotor.set(ControlMode.PercentOutput, .25);
        }
        rotatingDrumMotor.setSelectedSensorPosition(0);

    }

}
