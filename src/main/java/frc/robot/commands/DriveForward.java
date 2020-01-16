package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveForward extends CommandBase{
    double velocity;

    public DriveForward() {
        // Use addRequirements() here to declare subsystem dependencies.
        super();
        addRequirements(Robot.drivetrain);
      }
    
    
    // Called when the command is initially scheduled.
      public void initialize() {
        Robot.drivetrain.drive(velocity, 0);
      }
    
      // Called once the command ends or is interrupted.
      public void end() {
        Robot.drivetrain.drive(0, 0);
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return false;
      }
}