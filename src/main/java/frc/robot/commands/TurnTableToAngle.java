package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.subsystems.Turntable;

public class TurnTableToAngle extends CommandBase {
    private Turntable tt;
    private Limelight ll;

    public TurnTableToAngle(Turntable tt, Limelight ll) {
        addRequirements(tt);
        this.tt = tt;
        this.ll = ll;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double highSpeed = 0.15;
        double lowSpeed = 0.095;
        if  (Math.abs(this.ll.getHorizontalOffset()) > 7) {
            if (this.ll.getHorizontalOffset() > 0) {
                System.out.println("Right");
                this.tt.tableMotor.set(ControlMode.PercentOutput, highSpeed);
            } else {
                System.out.println("Left");
                this.tt.tableMotor.set(ControlMode.PercentOutput, -highSpeed);
            }
        } else {
            if (this.ll.getHorizontalOffset() > 0) {
                System.out.println("Right");
                this.tt.tableMotor.set(ControlMode.PercentOutput, lowSpeed);
            } else {
                System.out.println("Left");
                this.tt.tableMotor.set(ControlMode.PercentOutput, -lowSpeed);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Done");
        this.tt.tableMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished() {
        return (Math.abs(this.ll.getHorizontalOffset()) < 1);
    }
}
