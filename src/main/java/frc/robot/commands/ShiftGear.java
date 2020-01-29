package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrain.DriveGear;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShiftGear extends CommandBase{
    DriveGear gear;
    DriveTrain drivetrain;
    RobotContainer robotContainer;


    public ShiftGear(DriveGear gear, DriveTrain drivetrain, RobotContainer robotContainer) {
      this.gear = gear;
      this.drivetrain = drivetrain;
      this.robotContainer = robotContainer;
    }

    public void initialize() {
      drivetrain.setDriveGear(gear);
    }
    
      // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      DriveGear highGear = DriveGear.HIGH_GEAR;
      DriveGear lowGear = DriveGear.LOW_GEAR;
      if (robotContainer.isLeftTriggerPressed()) {
        drivetrain.setDriveGear(highGear);
      }
      if (robotContainer.isRightTriggerPressed()){
        drivetrain.setDriveGear(lowGear);
      }
    } 
    
      // Called once the command ends or is interrupted.
      //@Override
      //public void end(boolean interrupted) {
      //}
    
      // Returns true when the command should end.
    @Override
     public boolean isFinished() {
       return true;
    }
}