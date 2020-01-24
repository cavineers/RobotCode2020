package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TeleopDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax right1;
    private CANSparkMax right2;

    public DifferentialDrive differentialDrive;

    public DriveTrain() {
        left1 = new CANSparkMax(8, MotorType.kBrushless);
        left2 = new CANSparkMax(7, MotorType.kBrushless);
        right1 = new CANSparkMax(6, MotorType.kBrushless);
        right2 = new CANSparkMax(5, MotorType.kBrushless);

        differentialDrive = new DifferentialDrive(left1, right1);

        differentialDrive.setRightSideInverted(true);
        left2.follow(left1);
        right2.follow(right1);

        setDefaultCommand(new TeleopDrive(this));
    }
}
