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
        super(new PIDController(.01, 0, 0));
        getController().setTolerance(0.0);
        tableMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        output = getController().calculate(getMeasurement(), this.currentSetpoint);
        System.out.println("CurrentPos: " + getMeasurement());
        System.out.println("Wanted: " + this.currentSetpoint);
        System.out.println("OUTPUT: " +  output);
        output = output * -1;
        if (atTarget()) {
            tableMotor.pidWrite(MathUtil.clamp(0,-0.1,0.1));
        } else {
            tableMotor.pidWrite(MathUtil.clamp(output,-0.1,0.1));
        }


        /*if (getMeasurement() < this.currentSetpoint) {
            System.out.println("1");
            
            tableMotor.pidWrite(MathUtil.clamp(getController().calculate(getMeasurement(), this.currentSetpoint),0,0.1));
        } else {
            System.out.println("2");
            tableMotor.pidWrite(MathUtil.clamp(getController().calculate(getMeasurement(), this.currentSetpoint),-0.1,0));
        }*/
    }

    @Override
    public double getMeasurement() {
        return tableMotor.getSelectedSensorPosition();
    }

    public void turnToAngle(double angle) {
        System.out.println(angle);
        this.currentSetpoint = ((int)getMeasurement() + (int)((11.37)*angle))*-1;
        // this.currentSetpoint = (int)getMeasurement() + (int)angle;
        getController().setSetpoint(this.currentSetpoint);
    }

    public boolean atTarget() {
        boolean r = (this.currentSetpoint-11<getMeasurement() && this.currentSetpoint+11>getMeasurement());
        if (r) {
            disable();
            tableMotor.set(0);
        }
        return r;
    }
}
