package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class HomeHood extends CommandBase {
    private Hood hood;

    public HomeHood(Hood hood) {
        addRequirements(hood);
        this.hood = hood;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.hood.hoodMotor.set(ControlMode.PercentOutput, 0.40);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("homed");
        this.hood.hoodMotor.set(0);
        this.hood.hoodMotor.setSelectedSensorPosition(0);
    }

    @Override
    public boolean isFinished() {
        return this.hood.getLimitSwitch();
    }
}
