package frc.robot;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Dashboard {
    private RobotContainer rc;

    public Dashboard(RobotContainer rc) {
        this.rc = rc;
        this.configureDashboard();
        this.configureTuner();
    }   

    private void configureDashboard() {
        // Setup Power Distribution Panel (PDP)
        Shuffleboard.getTab("Dashboard")
        .add("PDP", this.rc.PDP)
        .withWidget(BuiltInWidgets.kPowerDistributionPanel)
        .withPosition(0, 1)
        .withSize(3, 2);

        // Setup differential drive
        Shuffleboard.getTab("Dashboard")
        .add("DriveTrain", this.rc.drivetrain.getDifferentialDrive())
        .withWidget(BuiltInWidgets.kDifferentialDrive)
        .withPosition(6, 1)
        .withSize(3, 2);

        // Setup compressor
        Shuffleboard.getTab("Dashboard")
        .add("Compressor", this.rc.compressor.enabled())
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withPosition(0, 3)
        .withSize(1, 1);
    }

    private void configureTuner() {
        Shuffleboard.getTab("Tuner")
        .add("Tuner", "Initializer");
    }
}