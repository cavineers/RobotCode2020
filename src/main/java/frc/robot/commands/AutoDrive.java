package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive extends CommandBase {
    private DriveTrain dt;
    boolean turning;
    double drive;
    double steer;
    double timeout;
    Timer timer;
    double time;

    public AutoDrive(DriveTrain dt, double drive, double steer, double timeout) {
        addRequirements(dt);
        this.dt = dt;
        this.drive = drive;
        this.steer = steer;
        this.timeout = timeout;
    }

    @Override
    public void initialize() {
        try {
            dt.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        time = timer.get();
        
    }

    @Override
    public void execute() {
        dt.drive(drive, steer);
    }

    @Override
    public boolean isFinished() {
        if (time - timer.get() == timeout) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        dt.drive(0, 0, false);
    }

}