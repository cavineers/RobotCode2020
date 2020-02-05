package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.BUTTON_MODE;


public class Climber extends SubsystemBase {

  CANSparkMax winchMotor = new CANSparkMax(Constants.Climber.MotorID, MotorType.kBrushless);
  private boolean brakeMode;

  public Climber() {
    this.getWinchMotor();
    this.setBrakeMode(true);
    winchMotor.setIdleMode(IdleMode.kBrake);
    winchMotor.setSmartCurrentLimit(38);
  }

  public CANSparkMax getWinchMotor() {
    return winchMotor;
  }

  public enum ClimberState {
    OFF, UP, DOWN
  }

  public ClimberState getClimberState(RobotContainer robotContainer){
    if (robotContainer.currentTriggerSetting == BUTTON_MODE.CLIMB){
      return ClimberState.UP;
    } else if ((robotContainer.currentTriggerSetting == BUTTON_MODE.CLIMB) && (robotContainer.isLeftTriggerPressed() == true)){
      return ClimberState.DOWN;
    } else {
      return ClimberState.OFF;
    }
  }

  public double getWinchMotorSpeed(RobotContainer robotContainer){
    double winchMotorSpeed;
    if (getClimberState(robotContainer) == Climber.ClimberState.UP) {
      winchMotorSpeed = -5800;
      return winchMotorSpeed;
   }else if (getClimberState(robotContainer) == Climber.ClimberState.OFF){
      winchMotorSpeed = 0;
      return winchMotorSpeed;
   }else if (getClimberState(robotContainer) == Climber.ClimberState.DOWN){
      winchMotorSpeed = 5800;
      return winchMotorSpeed;
   }else {
     return winchMotorSpeed = 0;
   }
  }

  private void setBrakeMode(boolean on) {
    if (brakeMode != on) {
      brakeMode = on;
    }
  }

  @Override
  public void periodic() {
    
  }
}