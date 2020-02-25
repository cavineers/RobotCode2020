package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class HomeHood extends CommandBase {
    private Hood hood;

    public HomeHood(Hood hood) {
        addRequirements(hood);
        this.hood = hood;
    }

    @Override
    public void initialize() {
        this.hood.home();
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
