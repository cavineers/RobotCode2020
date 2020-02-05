package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable;

public class TurntableToTarget extends CommandBase {
    private Turntable turntable;
    private int rotationBefore;
    private int wantedRotation;
    private boolean finished = false;
    private double speed = 12;

    public TurntableToTarget(Turntable turntable, double angle) {
        addRequirements(turntable);
        this.turntable = turntable;
        this.wantedRotation = (int)(this.turntable.tableMotor.getSelectedSensorPosition()+((4096/360)*angle));
        System.out.println("Turntable");
    }

    @Override
    public void initialize() {
        System.out.println("Before: " + this.rotationBefore);
        System.out.println("After: " + this.wantedRotation);
    }

    @Override
    public void execute() {
        // Tried and failed
        // this.turntable.tableMotor.set(ControlMode.Current, 12.0);
        // this.turntable.tableMotor.set(ControlMode.Velocity, 0.15);
        // this.turntable.tableMotor.set(ControlMode.Position, 20);
        this.turntable.tableMotor.set(ControlMode.PercentOutput, -0.05);

        System.out.println(this.wantedRotation - this.turntable.getTurntableDegree());
        // if (this.wantedRotation - this.turntable.getTurntableDegree() > 0) {
        //     this.turntable.tableMotor.set(ControlMode.Current, -this.speed);
        // } else
        // if (this.wantedRotation - this.turntable.getTurntableDegree() < 0) {
        //     this.turntable.tableMotor.set(ControlMode.Current, this.speed);
        // }
        // if (this.wantedRotation == this.turntable.getTurntableDegree()) {
        //     System.out.println("Target reached");
        //     this.finished = true;
        // }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
