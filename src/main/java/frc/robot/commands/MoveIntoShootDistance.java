/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.lib.Limelight;

public class MoveIntoShootDistance extends PIDCommand {
    private Limelight limelight = new Limelight();
    private DriveTrain dt;

    public MoveIntoShootDistance(DriveTrain dt) {
        super(
            new PIDController(0, 0, 0),
            // This should return the measurement
            () -> 0,
            // This should return the setpoint (can also be a constant)
            () -> 0,
            // This uses the output
            output -> {
            // Use the output here
            });
        addRequirements(dt);
        this.dt = dt;
        double KpAim = -0.1f;
        double KpDistance = -0.1f;
        double min_aim_command = 0.05f;

        double tx = limelight.getHorizontalOffset();
        double ty = limelight.getVerticalOffset();

        double heading_error = -tx;
        double distance_error = -ty;
        double steering_adjust = 0.0f;

        if (tx > 1.0) {
            steering_adjust = KpAim*heading_error - min_aim_command;
        } else
        if (tx < 1.0) {
            steering_adjust = KpAim*heading_error + min_aim_command;
        }

        double distance_adjust = KpDistance * distance_error;
        dt.drive(distance_adjust, steering_adjust);
        // left_command += steering_adjust + distance_adjust;
        // right_command -= steering_adjust + distance_adjust;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
