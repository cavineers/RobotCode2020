package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;

public class LimelightOn extends CommandBase {
    private Limelight ll;
    
    public LimelightOn(Limelight ll) {
        this.ll = ll;
    }

    @Override
    public void initialize() {
        this.ll.setLightMode(Limelight.LEDMode.OFF);
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
