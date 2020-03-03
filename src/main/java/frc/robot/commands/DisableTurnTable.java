package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurnTable;

public class DisableTurnTable extends InstantCommand {
    private TurnTable tt;

    public DisableTurnTable(TurnTable tt) {
        addRequirements(tt);
        this.tt = tt;
    }

    @Override
    public void initialize() {
        this.tt.setState(TurnTable.TurnTableState.OFF);
    }
}