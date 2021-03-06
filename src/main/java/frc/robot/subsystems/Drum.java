package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;


public class Drum extends PIDSubsystem {
    // Drum motor
    public WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Drum.MotorID);

    // Current setpoint
    public int currentSetpoint;

    private DigitalInput limitSwitch = new DigitalInput(Constants.Drum.LimitSwitch);

    private double lastTime = 0.0;

    private int ballCount = 0;

    private double lastBall = 0;

    /**
     * Drum constructor
     */
    public Drum() {
        // Set our PID values
        super(new PIDController(Constants.Drum.kP, Constants.Drum.kI, Constants.Drum.kD));

        // Set the tolerance
        getController().setTolerance(Constants.Drum.tolerance);

        // Encoder
        this.motor.setSelectedSensorPosition(0);

        // Break Mode
        this.motor.setNeutralMode(NeutralMode.Brake);

        // Set the initial ball count
        SmartDashboard.putNumber("ball_count", this.ballCount);
    }

    /**
     * Move to the next drum position
     */
    public void moveToNext() {
        this.currentSetpoint = this.currentSetpoint + (-((int)((409600/360)*(360/5))));
        setSetpoint(this.currentSetpoint);
        getController().setSetpoint(this.currentSetpoint);
    }

    /**
     * Move to the previous drum position
     */
    public void moveBack() {
        this.currentSetpoint = this.currentSetpoint - (-((int)((409600/360)*(360/5))));
        setSetpoint(this.currentSetpoint);
        getController().setSetpoint(this.currentSetpoint);
    }

    /**
     * Set the drum's setpoint
     * @param sp Setpoint in encoder pulses
     */
    public void makeSetpoint(int sp) {
        this.currentSetpoint = sp;
        setSetpoint(this.currentSetpoint);
        getController().setSetpoint(this.currentSetpoint);
    }

    /**
     * Get the current number of ball's stored
     * @return ball count
     */
    public int getBallCount() {
        return this.ballCount;
    }

    /**
     * Remove a ball from the count
     */
    public void removeBall() {
        if (this.ballCount != 0) {
            this.ballCount--;
            SmartDashboard.putNumber("ball_count", this.ballCount);

        }
    }

    /**
     * Add a ball to the count
     */
    public void addBall() {
        if (Timer.getFPGATimestamp()-this.lastBall > 1.5) {
            this.ballCount++;
            this.lastBall = Timer.getFPGATimestamp();
            SmartDashboard.putNumber("ball_count", this.ballCount);
        }
    }

    /**
     * Use the output generated by the PID
     */
    @Override
    public void useOutput(double output, double setpoint) {
        // Debugging logs
        // System.out.println("CurrentPos: " + getMeasurement());
        // System.out.println("Wanted: " + this.currentSetpoint);
        // System.out.println("OUTPUT: " +  output);

        // SmartDashboard.putNumber("drum_wants", this.currentSetpoint);
        // SmartDashboard.putNumber("drum_got", getMeasurement());
        // SmartDashboard.putNumber("drum_out", output);

        // Output
        // if (!this.isHoming) {
        this.motor.pidWrite(MathUtil.clamp(output,-Constants.Drum.speed,Constants.Drum.speed));
        // }
    }

    /**
     * Get the encoder position
     */
    @Override
    public double getMeasurement() {
        return this.motor.getSelectedSensorPosition();
    }

    /**
     * Periodic
     */
    public void DrumPeriodic() {
        if (Timer.getFPGATimestamp()-this.lastTime>0.75) {
            // System.out.println(this.motor.getSelectedSensorPosition());
            this.lastTime = Timer.getFPGATimestamp();
        }
    }

    /**
     * Is the limit switch pressed
     * @return true if pressed
     */
    public boolean isLimitPressed() {
        return !this.limitSwitch.get();
    }
}
