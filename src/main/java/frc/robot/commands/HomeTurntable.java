package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable;

public class HomeTurntable extends CommandBase {
    private Turntable tt;

    public HomeTurntable(Turntable tt) {
        addRequirements(tt);
        this.tt = tt;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.tt.getMotor().set(ControlMode.PercentOutput, 0.15);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Stopping");
        this.tt.getMotor().set(ControlMode.PercentOutput, 0);
        this.tt.getMotor().setSelectedSensorPosition(0);
    }

    @Override
    public boolean isFinished() {
        return this.tt.isLimitPressed();
    }
}
