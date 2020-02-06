package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable;

public class TurntableToTarget extends CommandBase {
    private double target;

    private Turntable table;

    public TurntableToTarget(Turntable tt, double target) {
        addRequirements(tt);
        this.target = target;
        this.table = tt;
    }

    @Override
    public void initialize() {
        table.enable();
        table.turnToAngle(5);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        table.disable();
    }

    @Override
    public boolean isFinished() {
        return table.atTarget();
    }
}
