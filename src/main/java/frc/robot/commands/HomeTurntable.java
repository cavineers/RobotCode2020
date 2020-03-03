package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurnTable;

public class HomeTurnTable extends CommandBase {
    private TurnTable tt;

    public HomeTurnTable(TurnTable tt) {
        addRequirements(tt);
        this.tt = tt;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.tt.tableMotor.set(ControlMode.PercentOutput, 0.15);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Stopping");
        this.tt.tableMotor.set(ControlMode.PercentOutput, 0);
        this.tt.tableMotor.setSelectedSensorPosition(0);
    }

    @Override
    public boolean isFinished() {
        return this.tt.isLimitPressed();
    }
}
