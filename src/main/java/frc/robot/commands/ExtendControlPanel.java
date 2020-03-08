package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ExtendControlPanel extends CommandBase {
    private ControlPanel cp;

    public ExtendControlPanel(ControlPanel cp) {
        addRequirements(cp);
        this.cp = cp;
    }

    @Override
    public void initialize() {
        this.cp.extend();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        // Finish immediately
        return true;
    }
}
