package frc.robot.commands.shoot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.commands.TurnTableToAngle;
import frc.robot.subsystems.TurnTable;

public class PerfectTurnTable extends CommandBase {
    // Subsystems
    private TurnTable tt;
    private Limelight ll;

    // Running time
    private double runTime;

    // Maximum run time
    private double maxRunTime = 3;

    public PerfectTurnTable(TurnTable tt, Limelight ll) {
        this.tt = tt;
        this.ll = ll;
    }

    @Override
    public void initialize() {
        new TurnTableToAngle(this.tt, this.ll).andThen(new TurnTableToAngle(this.tt, this.ll).andThen(new TurnTableToAngle(this.tt, this.ll)));
        this.runTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp()-this.runTime > this.maxRunTime;
    }
}
