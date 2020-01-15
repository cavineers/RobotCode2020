/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.IMUProtocol.GyroUpdate;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class TheGyro extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public float TheGyro_y=0;
  public short TheGyro_x = 0;
  public float TheGyro_z=0;
  public double gyroAngle = 0;

  public Gyro gyro =new Gyro(){
  
    @Override
    public void close() throws Exception {
      // TODO Auto-generated method stub
      
    }
  
    @Override
    public void reset() {
      // TODO Auto-generated method stub
      
    }
  
    @Override
    public double getRate() {
      // TODO Auto-generated method stub
      return 0;
    }
  
    @Override
    public double getAngle() {
      // TODO Auto-generated method stub
      return 0;
    }
  
    @Override
    public void calibrate() {
      // TODO Auto-generated method stub
      
    }
  };
  public GyroUpdate gyroUpdate = new GyroUpdate();
  
   
  
  public TheGyro() {

  }

  @Override
  public void periodic() {
  gyro.calibrate();
  gyroAngle=gyro.getAngle();
  TheGyro_x= gyroUpdate.gyro_x;
  TheGyro_y=gyroUpdate.gyro_y;
  TheGyro_z=gyroUpdate.gyro_z;

    
  }
  
}
