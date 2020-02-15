package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Deadzone;
import frc.robot.subsystems.DriveTrain;

public class TeleopDrive extends CommandBase {
    private DriveTrain dt;
    private Joystick joy;

    public TeleopDrive(DriveTrain dt, Joystick joy) {
        addRequirements(dt);
        this.dt = dt;
        this.joy = joy;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double steer = -Deadzone.add(joy.getRawAxis(4), 0.05);
        double drive = -Deadzone.add(joy.getRawAxis(1), 0.05);

        dt.drive(drive,steer,true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        dt.drive(0, 0, false);
    }
}
