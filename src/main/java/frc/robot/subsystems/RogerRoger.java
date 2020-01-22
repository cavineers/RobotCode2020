/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RogerRoger extends SubsystemBase {
  /**
   * Creates a new RogerRoger.
   */
    //solenoid and state
  private Solenoid pitchSolenoid;
  private boolean controlPanelState = false;

  public RogerRoger() {
    pitchSolenoid = new Solenoid(Constants.kControlPanelSolenoidCANid);
  }

  public void pitchUp() {
    controlPanelState = true;
    pitchSolenoid.set(controlPanelState);
  }

  public void pitchDown(){
    controlPanelState = false;
    pitchSolenoid.set(controlPanelState);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
