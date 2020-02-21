package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drum extends SubsystemBase {
     // Drum motor
     private WPI_TalonSRX drumMotor = new WPI_TalonSRX(Constants.Drum.MotorID);

     // DrumPosition
     public enum DrumPosition {
          POS_1,
          POS_2,
          POS_3,
          POS_4,
          POS_5,
          UNKNOWN,
          HOMING
     }

     // Current and wanted position
     private DrumPosition currentPosition = DrumPosition.UNKNOWN;
     private DrumPosition wantedPosition;

     // Limit switch
     DigitalInput homingSwitch = new DigitalInput(Constants.Drum.LimitSwitch);

     // Homing data
     private boolean homed = false;

     /**
      * constructor
      */
     public Drum() {
          // Configure motor
          drumMotor.setNeutralMode(NeutralMode.Brake);
     }

     @Override
     public void periodic() {
          if (this.currentPosition == DrumPosition.HOMING && !this.homingSwitch.get()) {
               this.drumMotor.set(ControlMode.PercentOutput, 0);
               this.homed = true;
               drumMotor.setSelectedSensorPosition(0); // reset encoder
               this.goToPos(DrumPosition.POS_1);
          }
     }

     public boolean withinRange(DrumPosition pos, int sensorPos) {
          int desiredSensorPosition = 0;
          if (pos == DrumPosition.POS_1) {
               desiredSensorPosition = 1000;
          } else
          if (pos == DrumPosition.POS_2) {
               desiredSensorPosition = 2000;
          } else
          if (pos == DrumPosition.POS_3) {
               desiredSensorPosition = 3000;
          } else
          if (pos == DrumPosition.POS_4) {
               desiredSensorPosition = 4000;
          } else
          if (pos == DrumPosition.POS_5) {
               desiredSensorPosition = 5000;
          }

          return (desiredSensorPosition - Constants.Drum.DrumLocationOffset < sensorPos && sensorPos < desiredSensorPosition + Constants.Drum.DrumLocationOffset);
     }

     public void goToPos(DrumPosition position) {
          this.wantedPosition = position;
     }

     public void home() {
          this.currentPosition = DrumPosition.HOMING;
          drumMotor.set(ControlMode.Velocity, 0.2);
          drumMotor.setSelectedSensorPosition(0); // reset encoder
     }

     public boolean isHomed() {
          return this.homed;
     }
}
