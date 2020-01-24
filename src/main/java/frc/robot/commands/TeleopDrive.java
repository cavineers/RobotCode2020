package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.networktables.*;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

import java.lang.Math;
import edu.wpi.first.wpilibj.controller.PIDController;

public class TeleopDrive extends CommandBase {
    private DriveTrain dt;
    private OI oi;

    public TeleopDrive(DriveTrain dt) {
        addRequirements(dt);
        this.oi = Robot.oi;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double steer = Robot.oi.addDeadZone(oi.getJoystick().getRawAxis(4));
        double drive = Robot.oi.addDeadZone(oi.getJoystick().getRawAxis(1));

        dt.differentialDrive.curvatureDrive(drive,steer,true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
