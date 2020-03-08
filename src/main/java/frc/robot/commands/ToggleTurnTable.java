package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurnTable;

public class ToggleTurnTable extends InstantCommand {
    private TurnTable tt;

    public ToggleTurnTable(TurnTable tt) {
        addRequirements(tt);
        this.tt = tt;
    }

    @Override
    public void initialize() {
        if (this.tt.getState() == TurnTable.TurnTableState.OFF) {
        this.tt.setState(TurnTable.TurnTableState.ON);
        } else {
        this.tt.setState(TurnTable.TurnTableState.OFF);
        }
    }
}
