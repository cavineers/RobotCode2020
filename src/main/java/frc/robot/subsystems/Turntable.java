package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.Robot;


public class Turntable extends PIDSubsystem {
    // turntable motor
    private WPI_TalonSRX tableMotor = new WPI_TalonSRX(Constants.Turntable.MotorID);

    // Current setpoint
    private int currentSetpoint;

    /**
     * Turntable
     */
    public Turntable() {
        // Set our PID values
        super(new PIDController(Constants.Turntable.kP, Constants.Turntable.kI, Constants.Turntable.kD));

        // Set the tolerance
        getController().setTolerance(Constants.Turntable.tolerance);

        // Encoder
        tableMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void useOutput(double output, double setpoint) {
        // Debugging logs
        Robot.logger.logln("CurrentPos: " + getMeasurement());
        Robot.logger.logln("Wanted: " + this.currentSetpoint);
        Robot.logger.logln("OUTPUT: " +  output);

        // Output
        tableMotor.set(MathUtil.clamp(output,-0.1,0.1));
    }

    @Override
    public double getMeasurement() {
        return tableMotor.getSelectedSensorPosition();
    }

    /**
     * turnToAngle
     * @param angle angle to turn to (in degrees)
     */
    public void turnToAngle(double angle) {
        Robot.logger.logln(angle);
        this.currentSetpoint = (int)((4096/360)*angle);
        setSetpoint(this.currentSetpoint);
        getController().setSetpoint(this.currentSetpoint);
    }

    /**
     * atTarget
     * @return whether we are at target
     */
    public boolean atTarget() {
        boolean r = (this.currentSetpoint-5<getMeasurement() && this.currentSetpoint+5>getMeasurement());
        if (r) {
            disable();
            tableMotor.set(0);
        }
        return r;
    }
}