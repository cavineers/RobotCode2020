package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CompressorController extends SubsystemBase {
    // Compressor
    private Compressor compressor = new Compressor(Constants.CANIds.PCM);
    
    // Compressor mode
    public enum CompressorMode {
        ENABLED,
        DISABLED
    }

    // Current compressor mode
    private CompressorMode currentMode = CompressorMode.DISABLED;

    // Manual mode data
    private boolean manualControl = false;

    /**
     * constructor
     */
    public CompressorController() {
        this(false);
    }

    /**
     * constructor
     * @param allowManualControl allow manual control of compressor
     */
    public CompressorController(boolean manualMode) {
        // Default to disabled (safety)
        this.makeMode(CompressorMode.DISABLED);
        this.manualControl = manualMode;
    }

    /**
     * compressor periodic
     */
    @Override
    public void periodic() {
        // System.out.println("Pressure switch value:" + compressor.getPressureSwitchValue()); //$ TESTING
        if (!this.manualControl) { // If not in manual control mode
            // if (compressor.getPressureSwitchValue()) {
            //     if (this.currentMode != CompressorMode.ENABLED) {
            //         this.makeMode(CompressorMode.ENABLED);
            //         System.out.println("Started compressor");
            //     }
            // } else {
            //     if (this.currentMode != CompressorMode.DISABLED) {
            //         this.makeMode(CompressorMode.DISABLED);
            //         System.out.println("Stopped compressor");
            //     }
            // }
        }
    }

    /**
     * set the compressor mode
     * @param mode the defined mode
     */
    public void setMode(CompressorMode mode) {
        if (this.manualControl) { // if in manual control
            this.makeMode(mode); // set the mode
        }
    }

    /**
     * set the compressor mode locally
     * @param mode the defined mode
     */
    private void makeMode(CompressorMode mode) {
        if (mode == CompressorMode.ENABLED) {
            this.compressor.start();
        } else {
            this.compressor.stop();
        }
    }

    /**
     * get the compressor's state
     * @return boolean of true if enabled
     */
    public boolean isEnabled() {
        return this.compressor.enabled();
    }
}
