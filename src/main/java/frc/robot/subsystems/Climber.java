package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
  private CANSparkMax winchMotor = new CANSparkMax(Constants.kClimberMotorCANid, MotorType.kBrushless);
  
  public Climber() {

  }

  @Override
  public void periodic() {}
}
