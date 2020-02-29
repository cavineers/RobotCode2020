package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.subsystems.Turntable;

public class TurntableToTarget extends CommandBase {
    private Limelight ll;

    private Turntable table;

    public TurntableToTarget(Turntable tt, Limelight ll) {
        this.ll = ll;
        this.table = tt;
        addRequirements(tt);
    }

    @Override
    public void initialize() {
        table.turnToAngle(this.ll.getHorizontalOffset());
        table.enable();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        System.out.println("finished");
        table.disable();
    }

    @Override
    public boolean isFinished() {
        return table.atTarget();
    }
}
