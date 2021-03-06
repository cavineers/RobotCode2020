package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.shoot.HighShooter;
import frc.robot.subsystems.TurnTable;

public class Autonomous extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    public Autonomous(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
        this.rc.turnTable.setState(TurnTable.TurnTableState.TARGETING);
    }

    @Override
    public void execute() { 
        this.rc.drivetrain.drive(-0.3, 0);
    }

    @Override
    public void end(boolean interrupted) {
        new HighShooter(this.rc).schedule();
        this.rc.drivetrain.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 0.9);
    }
}
