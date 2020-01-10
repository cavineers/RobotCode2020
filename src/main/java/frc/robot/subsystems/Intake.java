// TODO: Everything

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM; 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Subsystem;


import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.MotorSafety;


public class Intake extends SubsystemBase {
  public TalonSRX ballIntakeMotor = new TalonSRX(Constants.kIntakeMotorCANid);
private boolean isOn= false; 
public float drumPosition=0;
  public Intake() {
this.getIntakeMotor().setNeutralMode(NeutralMode.Brake);

  
  }
  @Override
  public void periodic() {}
  
  

  

  
  public TalonSRX getIntakeMotor() {
      return this.ballIntakeMotor;
  }
   
  
    
 
    if (isOn){
    
    ballIntakeMotor.set(ControlMode.PercentOutput,2);
    }

    
    else if (isOn==false){
    ballIntakeMotor.set(ControlMode.PercentOutput,0);
    }
    else{
ballIntakeMotor.set(ControlMode.PercentOutput,0);
    }
    
}
}
