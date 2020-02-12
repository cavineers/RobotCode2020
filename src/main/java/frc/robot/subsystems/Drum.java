package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drum extends SubsystemBase {
    public TalonSRX rotatingDrumMotor = new TalonSRX(Constants.kDrumMotorCANid);
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

    public enum DrumPosition {
        HOLE1, HOLE2, HOLE3, HOLE4, HOLE5, FULL, INVALID;
    }

    public Drum() {
        limitSwitch = new DigitalInput(Constants.kDrumLimitSwitch);

        irSensor = new AnalogInput(Constants.kIrSensor);
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
            goToDesiredPosition(getDesiredPosition());
            System.out.println(rotatingDrumMotor.getSelectedSensorPosition());
        } else {
            // System.out.println("Drum not ready");
        }
        if (drumReady == false) {
            final long startTime = System.currentTimeMillis();
            if (irSensor.getVoltage() > 1.75) {
                if (numberStopperIR == 0) {

                    while (startTime + 500 > System.currentTimeMillis()) {

                    }
                    numberOfBalls = numberOfBalls + 1;
                    numberStopperIR = 1;
                }

            }
        }
        if (irSensor.getVoltage() < 1.75) {
            numberStopperIR = 0;
        }

        System.out.println(numberOfBalls);
        // System.out.println(irSensor.getVoltage());
        if (irSensor.getVoltage() > 1.5) {
            System.out.println("true");
        } else if (irSensor.getVoltage() < 1.5) {
            System.out.println("false");
        } else {
            System.out.println("error");
        }
        // if (getLimitSwitch() == true) {
        // if (counter == 1) {
        // drumPosition = drumPosition + 1;
        // counter = 0;
        // }
        // }
        // if (getLimitSwitch() == false) {
        // counter = 1;
        // }
        if (numberOfBalls > 4 & irSensor.getVoltage() < 1.5) {
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
            break;
        case HOLE2:
            nextPosition();
            break;
        case HOLE3:

            nextPosition();
            break;

        case HOLE4:
            nextPosition();
            break;
        case HOLE5:

            while (rotatingDrumMotor.getSelectedSensorPosition() != 800) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > 800) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
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
