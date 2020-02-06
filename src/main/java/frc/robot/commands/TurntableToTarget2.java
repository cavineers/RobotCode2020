package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable2;

public class TurntableToTarget2 extends CommandBase {
    private double target;

    private Turntable2 table;

    public TurntableToTarget2(Turntable2 tt, double target) {
        addRequirements(tt);
        this.target = target;
        this.table = tt;
    }

    @Override
    public void initialize() {
        table.setPosition(this.target);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
