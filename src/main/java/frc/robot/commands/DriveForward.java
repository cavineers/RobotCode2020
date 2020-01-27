package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveForward extends CommandBase{
    double velocity;
    DriveTrain drivetrain;

    public DriveForward(DriveTrain drivetrain) {
        super();
        this.drivetrain = drivetrain;
      }
    
    
    // Called when the command is initially scheduled.
      public void initialize() {
        drivetrain.drive(velocity, 0);
      }
    
      // Called once the command ends or is interrupted.
      public void end() {
        drivetrain.drive(0, 0);
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return false;
      }
}