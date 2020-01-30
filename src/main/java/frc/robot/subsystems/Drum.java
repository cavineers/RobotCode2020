package frc.robot.subsystems;

import edu.wpi.first.hal.sim.DIOSim;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drum extends SubsystemBase {
    public TalonSRX rotatingDrumMotor = new TalonSRX(Constants.kDrumMotorCANid);
    DigitalInput limitSwitch;
    DIOSim photoSensor1;
    DIOSim photoSensor2;
    DIOSim photoSensor3;
    DIOSim photoSensor4;
    DIOSim photoSensor5;
    public boolean finishedShooting = false;
    public boolean readyToShoot = false;
    public boolean drumReady = false;
    public double numberStopper = 0;

    public enum DrumPosition {
        HOLE1, HOLE2, HOLE3, HOLE4, HOLE5, FULL, INVALID;
    }

    public Drum() {
        limitSwitch = new DigitalInput(Constants.kDrumLimitSwitch);
        photoSensor1 = new DIOSim(Constants.kPhotoSensor1);
        photoSensor2 = new DIOSim(Constants.kPhotoSensor2);
        photoSensor3 = new DIOSim(Constants.kPhotoSensor3);
        photoSensor4 = new DIOSim(Constants.kPhotoSensor4);
        photoSensor5 = new DIOSim(Constants.kPhotoSensor5);
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
            System.out.println("Drum not ready");
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

    // get photo senor 1 value
    public boolean getPhotoSensor1() {
        return photoSensor1.getValue();
    }

    // get photo sensor 3 value
    public boolean getPhotoSensor2() {
        return photoSensor2.getValue();
    }

    // get photo sensor 3 value
    public boolean getPhotoSensor3() {
        return photoSensor3.getValue();
    }

    // get photo sensor 4 value
    public boolean getPhotoSensor4() {
        return photoSensor4.getValue();
    }

    // get photo sensor 5 value
    public boolean getPhotoSensor5() {
        return photoSensor5.getValue();
    }

    // find the open drum hole
    public DrumPosition getDesiredPosition() {
        if (getPhotoSensor1() == false) {
            return DrumPosition.HOLE1;
        } else if (getPhotoSensor2() == false) {
            return DrumPosition.HOLE2;
        } else if (getPhotoSensor3() == false) {
            return DrumPosition.HOLE3;
        } else if (getPhotoSensor4() == false) {
            return DrumPosition.HOLE4;
        } else if (getPhotoSensor5() == false) {
            return DrumPosition.HOLE5;
        } else if (getPhotoSensor1() == true && getPhotoSensor2() == true && getPhotoSensor3() == true
                && getPhotoSensor4() == true && getPhotoSensor5() == true) {
            return DrumPosition.FULL;
        } else {
            return DrumPosition.INVALID;
        }
    }

    // go tp the desired drum hole
    public void goToDesiredPosition(DrumPosition position) {
        switch (position) {
        case HOLE1:
            homeDrum();
            break;
        case HOLE2:

            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 2) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 2) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE3:

            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 3) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 3) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE4:

            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 4) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 4) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE5:

            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 5) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 5) {
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

    // home the drum
    public void homeDrum() {
        while (getLimitSwitch() == false) {
            rotatingDrumMotor.set(ControlMode.PercentOutput, .25);
        }
        rotatingDrumMotor.setSelectedSensorPosition(0);

    }

}
