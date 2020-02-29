package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class HoodToAngle extends CommandBase {
    private Hood hood;
    private double setpoint = 0.0;

    public HoodToAngle(Hood hood, double sp) {
        this.hood = hood;
        this.setpoint = sp;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        System.out.println("Setpoint: " + this.setpoint);
        // System.out.println("Actual: " + this.hood.getMeasurement());
        double highSpeed = 0.26;
        double lowSpeed = 0.2;
        if  (Math.abs(this.hood.getMeasurement()) > 20) {
            if (this.hood.getMeasurement() < this.setpoint) {
                System.out.println("Up");
                this.hood.hoodMotor.set(ControlMode.PercentOutput, -highSpeed);
            } else {
                System.out.println("Down");
                this.hood.hoodMotor.set(ControlMode.PercentOutput, highSpeed);
            }
        } else {
            if (this.hood.getMeasurement() < this.setpoint) {
                System.out.println("Up");
                this.hood.hoodMotor.set(ControlMode.PercentOutput, -lowSpeed);
            } else {
                System.out.println("Down");
                this.hood.hoodMotor.set(ControlMode.PercentOutput, lowSpeed);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Done");
        this.hood.hoodMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished() {
        return (Math.abs(this.hood.getMeasurement()-this.setpoint) < 6);
    }
}
