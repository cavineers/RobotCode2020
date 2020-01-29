/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
        if (drumReady == true) {
            goToDesiredPosition(getDesiredPosition());
            System.out.println(rotatingDrumMotor.getSelectedSensorPosition());
        }
    }

    public TalonSRX getDrumMotor() {

        return this.rotatingDrumMotor;

    }

    public double getPosition() {
        return rotatingDrumMotor.getSelectedSensorPosition();
    }

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }

    public boolean getPhotoSensor1() {
        return photoSensor1.getValue();
    }

    public boolean getPhotoSensor2() {
        return photoSensor2.getValue();
    }

    public boolean getPhotoSensor3() {
        return photoSensor3.getValue();
    }

    public boolean getPhotoSensor4() {
        return photoSensor4.getValue();
    }

    public boolean getPhotoSensor5() {
        return photoSensor5.getValue();
    }

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

    public void goToDesiredPosition(DrumPosition position) {
        switch (position) {
        case HOLE1:
            rotatingDrumMotor.setSelectedSensorPosition(0);
            while (rotatingDrumMotor.getSelectedSensorPosition() != Constants.kDrumEncoderPPI / 5) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > Constants.kDrumEncoderPPI / 5) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE2:
            rotatingDrumMotor.setSelectedSensorPosition(0);
            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 2) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 2) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE3:
            rotatingDrumMotor.setSelectedSensorPosition(0);
            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 3) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 3) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE4:
            rotatingDrumMotor.setSelectedSensorPosition(0);
            while (rotatingDrumMotor.getSelectedSensorPosition() != (Constants.kDrumEncoderPPI / 5) * 4) {
                if (rotatingDrumMotor.getSelectedSensorPosition() > (Constants.kDrumEncoderPPI / 5) * 4) {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, .5);
                } else {
                    rotatingDrumMotor.set(ControlMode.PercentOutput, -.5);
                }
            }
            break;
        case HOLE5:
            rotatingDrumMotor.setSelectedSensorPosition(0);
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
            break;
        case INVALID:
            System.out.println("ERROR");
            break;

        }

    }

    public void homeDrum() {
        while (getLimitSwitch() == false) {
            rotatingDrumMotor.set(ControlMode.PercentOutput, .25);
        }

    }

}
