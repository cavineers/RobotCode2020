package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RemoveFromHood extends CommandBase {
    public RemoveFromHood() {
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("hood_angle", SmartDashboard.getNumber("hood_angle", 0)-5);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
