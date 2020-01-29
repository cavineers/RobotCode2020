package frc.robot;
 
public final class Constants {
    //? All constants begin with a lowercase 'k'

    // CAN network mapping
    public static int kPneumaticsControlCANid      = 0;  // Pneumatic Control Module (PCM)
    public static int kDriveTrainMotor2CANid       = 1;  // Right 1 (neo)
    public static int kDriveTrainMotor4CANid       = 2;  // Right 2 (neo)
    public static int kDriveTrainMotor3CANid       = 3;  // Left 2 (neo)
    public static int kDriveTrainMotor1CANid       = 4;  // Left 1 (neo)
    public static int kFlyWheelMotorCANid          = 6;  // The shooter's fly wheel motor
    public static int kClimberMotorCANid           = 5;  // Climber motor
    public static int kDrumMotorCANid              = 7;  // Intakes drum motor
    public static int kShooterVerticalPitchCANid   = 8;  // Shooter's vertical pitch
    public static int kFeederMotorCANid            = 9;  // Feeder motor
    public static int kIntakeMotorCANid            = 10; // Intake motor
    public static int kControlPanelMotorCANid      = 11; // Control Panel rotator
    public static int kTurretRotationMotorCANid    = 12; // Turret rotation motor
    public static int kPowerDistributionPanelCANid = 35; // Power distribution panel (PDP)

    // VISION
    public static double kFieldGoalHeightFromGround = 53.0; // 31 inches used for testing
    public static double kLimelightHeightFromGround = 18.0; // vertical distance from limelight to ground
    public static double kLimelightMountingAngle    = 0;

    // Turntable
    public static double kTurntableSpeed = 0.06;

    // Shooter
    public static double kShooterMotorPIDp = 0.0;
    public static double kShooterMotorPIDi = 0.0;
    public static double kShooterMotorPIDd = 0.0;
    public static int kShooterCurrentLimit = 39;

    // tmp
    public static int[] kShooterEncoderPorts = new int[]{4, 5};
    public static int kEncoderCPR = 1024;
    public static double kEncoderDistancePerPulse = 1.0 / (double) kEncoderCPR;
    public static double kShooterFreeRPS = 5300;
    public static double kShooterTargetRPS = 4000;
    public static double kShooterToleranceRPS = 50;
    public static double kFeederVolts = 0.05;
    public static double kVVoltSecondsPerRotation = 12.0 / kShooterFreeRPS;
    public static double kFeederSpeed = 0.5;

    // Drum
    public static double kDrumDiameter   = 22.0; // in inches
    public static double kDrumEncoderPPI = (4096 / (kDrumDiameter * Math.PI)); // PPI

    // Control Panel
    public static int kControlPanelPCMid = 1;
}