package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turntable2 extends SubsystemBase {

    private WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.Turntable.MotorID);

    public Turntable2() {
        tableMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    }

    @Override
    public void periodic() {
        // System.out.println("Wanted: " + );
    }

    public void setPosition(double angle) {
        int targetPositionRotations = (int)(11.37*angle);
        System.out.println("Set Position: "+targetPositionRotations);
        System.out.println(tableMotor.getSelectedSensorPosition()+targetPositionRotations);
        tableMotor.set(ControlMode.Position, tableMotor.getSelectedSensorPosition()+targetPositionRotations);
    }

}
