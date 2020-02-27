package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drum extends SubsystemBase {
     // Drum motor
     public WPI_TalonSRX drumMotor = new WPI_TalonSRX(Constants.Drum.MotorID);

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

     private double lastTime = 0.0;

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

     /**
      * rotate to the next position
      */
     public void rotateNext() {
          this.drumMotor.set(ControlMode.Position, this.drumMotor.getSelectedSensorPosition()+Constants.Drum.PositionOffset);
     }

     /**
      * home the drum
      */
     public void home() {
          this.currentPosition = DrumPosition.HOMING;
          drumMotor.set(ControlMode.Velocity, 0.2);
          drumMotor.setSelectedSensorPosition(0); // reset encoder
     }

     /**
      * Is the drum homed
      * @return
      */
     public boolean isHomed() {
          return this.homed;
     }

     /**
      * Drum periodic
      */
     @Override
     public void periodic() {
          if (this.currentPosition == DrumPosition.HOMING && !this.homingSwitch.get()) {
               this.drumMotor.set(ControlMode.PercentOutput, 0);
               this.homed = true;
               drumMotor.setSelectedSensorPosition(0); // reset encoder
          }
          if (Timer.getFPGATimestamp()-this.lastTime > 0.25) {
               // System.out.println("Limit Switch:" + this.homingSwitch.get());
               this.lastTime = Timer.getFPGATimestamp();
           }
     }
}
