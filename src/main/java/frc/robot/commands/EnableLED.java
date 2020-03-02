/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Arduino;

public class EnableLED extends InstantCommand {
  private final Arduino colorSensorNano;

  public EnableLED(final Arduino colorSensorNano) {

    addRequirements(colorSensorNano);
    this.colorSensorNano = colorSensorNano;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.colorSensorNano.enableLED();
  }
}
