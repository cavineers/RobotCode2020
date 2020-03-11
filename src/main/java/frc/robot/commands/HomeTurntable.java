package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurnTable;

public class HomeTurntable extends CommandBase {
    private TurnTable tt;

    private double time = 0.0;

    private boolean finished = false;
    
    public HomeTurntable(TurnTable tt) {
        addRequirements(tt);
    }

    @Override
    public void initialize() {
        this.tt.setState(TurnTable.TurnTableState.OFF);
        this.time = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if (Timer.getFPGATimestamp()-this.time<0.5) {
            this.tt.tableMotor.set(-0.2);
        } else {
            if (this.tt.isLimitPressed()) {
                this.tt.tableMotor.set(0);
                this.tt.tableMotor.setSelectedSensorPosition(0);
                this.finished = true;
            } else {
                this.tt.tableMotor.set(0.2);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.tt.tableMotor.set(0);
        this.tt.setState(TurnTable.TurnTableState.NEUTRAL);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
