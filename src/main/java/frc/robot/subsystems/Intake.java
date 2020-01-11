// TODO: DrumMotor

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;


public class Intake extends SubsystemBase {

  public TalonSRX ballIntakeMotor = new TalonSRX(Constants.kIntakeMotorCANid);
  public TalonSRX DrumMotor = new TalonSRX(Constants.kDrumMotorCANid);

  private boolean isOn= false;
  private boolean drumON = false; 
  private boolean drumOnT= false;

  public float drumPosition=0;
  
  public Intake() {

    this.getIntakeMotor().setNeutralMode(NeutralMode.Brake);
    this.getDrumMotor().setNeutralMode(NeutralMode.Brake);

  }

  @Override

  public void periodic() {
  
    if (isOn){
    
        ballIntakeMotor.set(ControlMode.PercentOutput,2);

      }
  
      else if (isOn==false){

        ballIntakeMotor.set(ControlMode.PercentOutput,0);

      }

      else{

        ballIntakeMotor.set(ControlMode.PercentOutput,0);
      
      }
      DrumMotor.getSelectedSensorPosition();
      if(drumPosition<5.1&&drumON&&360-drumPosition*71.9>DrumMotor.getSelectedSensorPosition()){
       
        DrumMotor.set(ControlMode.Position,drumPosition*72);
        
        drumOnT=true;
      
        }
     else{

       drumON=false;
       
     }
        
        

      

  }
  
  public TalonSRX getIntakeMotor() {

      return this.ballIntakeMotor;

  } 

  public TalonSRX getDrumMotor(){

    return this.DrumMotor;

  }
    
}

