package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Turntable extends PIDSubsystem {

    private WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.Turntable.MotorID);

    public Turntable() {
        super(new PIDController(0.0005, 0, 0));
        getController().setTolerance(0.0);
        // getController().disableContinuousInput();
    }

    @Override
    public void useOutput(double output, double setpoint) {
        System.out.println("Output: " + output);
        System.out.println("Setpoint: " + setpoint);
        tableMotor.pidWrite(output);
    }

    @Override
    public double getMeasurement() {
        return tableMotor.getSelectedSensorPosition();
    }

    public void turnToAngle(double angle) {
        // int pos = tableMotor.getSelectedSensorPosition() + (int)((4096/360)*angle);
        System.out.println("Angle: " + (angle));
        getController().setSetpoint(tableMotor.getSelectedSensorPosition()+200);
    }

    public boolean atTarget() {
        return getController().atSetpoint();
    }
}
