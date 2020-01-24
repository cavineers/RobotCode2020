package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TeleopDrive;

public class DriveTrain extends SubsystemBase {
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax right1;
    private CANSparkMax right2;

    private DifferentialDrive differentialDrive;

    public DriveTrain(Joystick joy) {
        left1 = new CANSparkMax(8, MotorType.kBrushless);
        left2 = new CANSparkMax(7, MotorType.kBrushless);
        right1 = new CANSparkMax(6, MotorType.kBrushless);
        right2 = new CANSparkMax(5, MotorType.kBrushless);

        differentialDrive = new DifferentialDrive(left1, right1);

        differentialDrive.setRightSideInverted(true);
        left2.follow(left1);
        right2.follow(right1);

        setDefaultCommand(new TeleopDrive(this, joy));
    }

    public void drive(double drive, double steer, boolean quickTurn) {
        this.differentialDrive.curvatureDrive(drive, steer, quickTurn);
    }

    public void drive(double drive, double steer) {
        this.differentialDrive.curvatureDrive(drive, steer, false);
    }
}
