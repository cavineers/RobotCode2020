package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;


public class Turntable extends PIDSubsystem {
    private int currentSetpoint;
    private WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.Turntable.MotorID);

    public Turntable() {
        super(new PIDController(0.005, 0, 0));
        getController().setTolerance(0.0);
        tableMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        System.out.println("CurrentPos: " + getMeasurement());
        System.out.println("Wanted: " + this.currentSetpoint);
        if (getMeasurement() > this.currentSetpoint) {
            tableMotor.pidWrite(MathUtil.clamp(getController().calculate(getMeasurement(), this.currentSetpoint),-0.1,0.1));
        } else {
            tableMotor.pidWrite(-MathUtil.clamp(getController().calculate(getMeasurement(), this.currentSetpoint),-0.1,0.1));
        }
    }

    @Override
    public double getMeasurement() {
        return tableMotor.getSelectedSensorPosition();
    }

    public void turnToAngle(double angle) {
        this.currentSetpoint = (int)getMeasurement() + (int)((4096/360)*angle);
        getController().setSetpoint(this.currentSetpoint);
    }

    public boolean atTarget() {
        return (this.currentSetpoint-11<getMeasurement() && this.currentSetpoint+11>getMeasurement());
    }
}
