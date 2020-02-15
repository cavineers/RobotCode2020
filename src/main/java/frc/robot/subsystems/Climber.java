package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    // Climber NEO
    public CANSparkMax climberMotor = new CANSparkMax(Constants.Climber.MotorID, MotorType.kBrushless);

    // Create Climber Mode
    public enum ClimberMode {
        EXTENDING,
        RETRACTING,
        STOPPED
    }

    // Current mode
    private ClimberMode currentMode = ClimberMode.STOPPED;

    // Constructor
    public Climber() {
        // Configure current limiting on the NEO (38amps)
        this.climberMotor.setSmartCurrentLimit(Constants.Climber.CurrentLimit);
    }

    @Override
    public void periodic() {} // Empty periodic

    /**
     * setMode
     * @param mode the desired climbing mode
     */
    public void setMode(ClimberMode mode) {
        if (mode == ClimberMode.EXTENDING) {
            this.climberMotor.set(Constants.Climber.UpwardRPM);
        } else
        if (mode == ClimberMode.RETRACTING) {
            this.climberMotor.set(Constants.Climber.DownwardRPM);
        } else
        if (mode == ClimberMode.STOPPED) {
            this.climberMotor.set(0);
        }
        this.currentMode = mode;
    }

    /**
     * getMode
     * @return current climber mode
     */
    public ClimberMode getMode() {
        return this.currentMode;
    }
}
