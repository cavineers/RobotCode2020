// TODO: Everything

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Climber extends SubsystemBase {

  CANSparkMax winchMotor = new CANSparkMax(Constants.kClimberMotorCANid, MotorType.kBrushless);
  private boolean brakeMode;

  public Climber() {
    this.getWinchMotor();
    this.setBrakeMode(true);
    winchMotor.setSmartCurrentLimit(28);
  }

  public CANSparkMax getWinchMotor() {
    return winchMotor;
  }

  public enum ClimberState {
    OFF, ON
  }

  public ClimberState getClimberState(RobotContainer robotContainer){
    if (robotContainer.lastDpad == 90){
      return ClimberState.ON;
    } else {
      return ClimberState.OFF;
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
