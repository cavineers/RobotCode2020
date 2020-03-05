package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DumbDrum extends SubsystemBase {
    // Drum motor
    public WPI_TalonSRX motor = new WPI_TalonSRX(Constants.Drum.MotorID);

    // Limit switch
    private DigitalInput limitSwitch = new DigitalInput(Constants.Drum.LimitSwitch);

    // Stage
    private int stage = 0;

    /**
     * Constructor
     */
    public DumbDrum() {
        // Brake mode
        this.motor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Move to next position on the drum
     */
    public void moveToNext() {
        this.stage++;
    }

    /**
     * Is the limit switch pressed
     * @return true if pressed
     */
    public boolean isLimitPressed() {
        return !this.limitSwitch.get();
    }

    /**
     * Periodic things
     */
    @Override
    public void periodic() {
        // Traveling to the edge of the first hump
        if (this.stage == 1) {
            if (this.isLimitPressed()) {
                this.motor.set(0);
                this.stage++;
            } else {
                this.motor.set(-0.2);
            }
        }

        // Crossing the hump
        if (this.stage == 2) {
            if (!this.isLimitPressed()) {
                this.motor.set(0);
                this.stage++;
            } else {
                this.motor.set(-0.2);
            }
        }

        // Traveling to the edge of the next hump
        if (this.stage == 3) {
            if (this.isLimitPressed()) {
                this.motor.set(0);
                this.stage = 0;
            } else {
                this.motor.set(-0.2);
            }
        }
    }
}
