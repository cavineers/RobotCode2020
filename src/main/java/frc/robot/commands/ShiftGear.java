package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain.DriveGear;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShiftGear extends CommandBase{
    DriveGear gear;


    public ShiftGear(DriveGear gear) {
      this.gear = gear;
    }

    public void initialize() {
      Robot.drivetrain.setDriveGear(gear);
    }
    
      // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      /*DriveGear highGear = DriveGear.HIGH_GEAR;
      DriveGear lowGear = DriveGear.LOW_GEAR;
      if (Robot.m_robotContainer.isLeftTriggerPressed){
        Robot.drivetrain.setDriveGear(highGear);
      }
      if (Robot.m_robotContainer.isRightTriggerPressed){
        Robot.drivetrain.setDriveGear(lowGear);
      }*/
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