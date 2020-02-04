package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turntable;

public class TurntableToTarget extends CommandBase {
    private Turntable turntable;
    private double rotationBefore;
    private double wantedRotation;
    private boolean finished = false;

    public TurntableToTarget(Turntable turntable, double angle) {
        addRequirements(turntable);
        this.turntable = turntable;
        this.wantedRotation = angle;
        System.out.println("Turntable");
    }

    @Override
    public void initialize() {
        this.rotationBefore = turntable.getTurntableDegree();
        System.out.println(this.wantedRotation);
    }

    @Override
    public void execute() {
        System.out.println("Wanted: " +this.wantedRotation);
        System.out.println("Current: " +this.turntable.getTurntableDegree());
        if (this.wantedRotation > turntable.getTurntableDegree()) {
            this.turntable.setMotorRotation(Turntable.TurntableMode.ROTATE_RIGHT);
        } else
        if (this.wantedRotation < turntable.getTurntableDegree()) {
            this.turntable.setMotorRotation(Turntable.TurntableMode.ROTATE_LEFT);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }
}
