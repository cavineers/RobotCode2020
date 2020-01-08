//! UNTESTED

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.ControlColoring;
import frc.lib.ControlColoring.ControlColor;

public class ColorSensor extends SubsystemBase {
    // Coloring Helper Library
    private ControlColoring coloring;

    // Last known color
    private ControlColor lastKnown;
    
  /**
   * Creates a new ExampleSubsystem.
   */
  public ColorSensor() {
    this.coloring = new ControlColoring();
    this.lastKnown = ControlColor.NO_COLOR;
  }

  public ControlColor getRobotLast() {
      return this.lastKnown;
  }

  public ControlColor getFieldLast() {
    return coloring.transposeToField(this.lastKnown);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // TODO: poll color sensor and save data
  }
}
