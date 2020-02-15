package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Turntable;

public class StopTurntable extends InstantCommand {
    private Turntable tt;

    public StopTurntable(Turntable tt) {
        addRequirements(tt);
        this.tt = tt;
    }

    @Override
    public void initialize() {
        tt.disable();
    }
}
