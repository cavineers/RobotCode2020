package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ToggleControlPanel extends CommandBase {
    private ControlPanel cp;

    public ToggleControlPanel(ControlPanel cp) {
        addRequirements(cp);
        this.cp = cp;
    }

    @Override
    public void initialize() {
        if (this.cp.getPistonPosition() == ControlPanel.ControlPanelPosition.RETRACTED) {
            this.cp.extend();
        } else {
            this.cp.retract();
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
