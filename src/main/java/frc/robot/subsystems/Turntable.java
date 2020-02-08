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
        //10, 1,1.4
        super(new PIDController(.1, 0, .014));
        getController().setTolerance(55.0);
        tableMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        System.out.println("CurrentPos: " + getMeasurement());
        System.out.println("Wanted: " + this.currentSetpoint);
        System.out.println("OUTPUT: " +  output);
        tableMotor.set(MathUtil.clamp(output,-0.1,0.1));
    }

    @Override
    public double getMeasurement() {
        return tableMotor.getSelectedSensorPosition();
    }

    public void turnToAngle(double angle) {
        System.out.println(angle);
        this.currentSetpoint = (int)((4096/360)*angle);
        setSetpoint(this.currentSetpoint);
        getController().setSetpoint(this.currentSetpoint);
    }

    public boolean atTarget() {
        boolean r = (this.currentSetpoint-5<getMeasurement() && this.currentSetpoint+5>getMeasurement());
        if (r) {
            disable();
            tableMotor.set(0);
        }
        return r;

    }

    public WPI_TalonSRX getMotor(){
        return this.tableMotor;
    }
}
