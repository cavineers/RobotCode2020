/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    public CANSparkMax climberMotor = new CANSparkMax(Constants.kClimberMotorCANid, MotorType.kBrushless);

    public enum ClimberMode {
        EXTENDING,
        RETRACTING,
        STOPPED
    }

    public Climber() {
        this.climberMotor.setSmartCurrentLimit(38);
    }

    @Override
    public void periodic() {
    }

    public void setMode(ClimberMode mode) {
        if (mode == ClimberMode.EXTENDING) {
            this.climberMotor.set(-5800);
        } else
        if (mode == ClimberMode.RETRACTING) {
            this.climberMotor.set(5800);
        } else
        if (mode == ClimberMode.STOPPED) {
            this.climberMotor.set(0);
        }
    }
}
