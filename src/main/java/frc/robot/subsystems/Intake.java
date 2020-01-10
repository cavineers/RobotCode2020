// TODO: Everything

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM; 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Subsystem;
import com.ctre.phoenix.sensors.CANCoder; 
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.CANifier;


public class Intake extends SubsystemBase {
  public TalonSRX ballIntakeMotor = new TalonSRX(Constants.kIntakeMotorCANid);
private boolean isOn= false; 
public float intakePosition=0;
  public Intake() {
this.getIntakeMotor().setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    
  }
 

    /**
     * get the talon of elevator motor
     * @return talon of elevator motor
     */
    public TalonSRX getIntakeMotor() {
        return this.ballIntakeMotor;
    }
   public void getTargetPosition(){
   
   }
    
}
