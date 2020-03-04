package frc.robot;

public final class Constants {
    // ? All constants begin with a lowercase 'k'

    // CAN network mapping
    public static class CANIds {
        public static int DriveTrainMotor4 = 2; // Right 2 (neo)
        public static int DriveTrainMotor2 = 1; // Right 1 (neo)
        public static int PCM = 0; // Pneumatic Control Module (PCM)
        public static int DriveTrainMotor3 = 3; // Left 2 (neo)
        public static int DriveTrainMotor1 = 4; // Left 1 (neo)
        public static int ShooterMotor = 6; // The shooter's fly wheel motor
        public static int DrumMotor = 7; // Intakes drum motor
        public static int ClimberMotor = 5; // Climber motor
        public static int ShooterVerticalPitch = 8; // Shooter's vertical pitch
        public static int IntakeMotor = 10; // Intake motor
        public static int FeederMotor = 9; // Feeder motor
        public static int ControlPanelMotor = 11; // Control Panel rotator
        public static int TurretRotationMotor = 12; // Turret rotation motor
        public static int PowerDistributionPanel = 30; // Power Distribution Panel (PDP)
    }

    // PCM module mapping
    public static class PCMChannels {
        public static int DriveShift1 = 6;
        public static int DriveShift2 = 7;
        public static int ControlPanel1 = 0;
        public static int ControlPanel2 = 1;

    }

    // VISION
    public static class Vision {
        public static double kFieldGoalHeightFromGround = 98.0; // 31 inches used for testing
        public static double kLimelightHeightFromGround = 18.0; // vertical distance from limelight to ground
    }

    public static double kLimelightMountingAngle = 25.0;

    public static class Turntable {
        // Turntable
        public static int MotorID = CANIds.TurretRotationMotor;
        public static double speed = 0.09;
        public static double kP = 0.1; // was 10
        public static double kI = 0.0; // was 1
        public static double kD = 0.014; // was 1.4
        public static double tolerance = 55.0; // ! Don't change this or everything will break for some reason
    }

    // Shooter
    public static class Shooter {
        public static int ShootID = CANIds.ShooterMotor;
        public static double PIDp = 0.0005;
        public static double PIDi = 0.0;
        public static double PIDd = 0.0001;
        public static double PIDiz = 0.0;
        public static double MaxOutput = 1.0;
        public static double MinOutput = -1.0;
        public static double MaxRPM = 5800;
        public static int ShooterCurrentLimit = 39;
    }

    public static class Drum {
        // Drum
        public static double kDrumDiameter = 22.0; // in inches
        public static int MotorID = CANIds.DrumMotor;
        public static double kDrumEncoderPPI = (4096 / (kDrumDiameter * Math.PI)); // PPI
        public static int LimitSwitch = 0;
        public static int IrSensor = 1;
        public static int DrumLocationOffset = 10;
        public static double IrSensorTrigger = 1.75;

    }

    public static class ControlPanel {
        // Control Panel
        public static int PCM = CANIds.PCM;
        public static int PCMChannel1 = PCMChannels.ControlPanel1;
        public static int PCMChannel2 = PCMChannels.ControlPanel2;
        public static int MotorID = CANIds.ControlPanelMotor;
    }

    // DriveTrain
    public static class DriveTrain {
        public static int PCMChannel1 = PCMChannels.DriveShift1;
        public static int PCM = CANIds.PCM;
        public static int PCMChannel2 = PCMChannels.DriveShift2;
        public static int DriveTrainMotor1 = CANIds.DriveTrainMotor1;
        public static int DriveTrainMotor2 = CANIds.DriveTrainMotor2;
        public static int DriveTrainMotor3 = CANIds.DriveTrainMotor3;
        public static int DriveTrainMotor4 = CANIds.DriveTrainMotor4;
        public static int WheelDiameter = 0;
    }

    // Climber
    public static class Climber {
        public static int UpwardRPM = -5800;
        public static int MotorID = CANIds.ClimberMotor;
        public static int DownwardRPM = 5800;
    }

    public static int CurrentLimit = 38;

    public static class ColorSensor {
        // Color Sensor
        public static int SerialBaudRate = 9600; // 9600 or 115200 (source:
                                                 // https://www.chiefdelphi.com/t/roborio-and-serial-data/144312/2)
    }

    public static int kIrSensor = 0;// Intakes IR sensor
}