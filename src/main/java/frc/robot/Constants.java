package frc.robot;
 
public final class Constants {
    // CAN network mapping
    public static class CANIds {
        public static int PCM                    = 0;  // Pneumatic Control Module (PCM)
        public static int DriveTrainMotor2       = 3;  // Right 1 (neo)
        public static int DriveTrainMotor4       = 4;  // Right 2 (neo)
        public static int DriveTrainMotor3       = 2;  // Left 2 (neo)
        public static int DriveTrainMotor1       = 1;  // Left 1 (neo)
        public static int ShooterMotor           = 6;  // The shooter's fly wheel motor
        public static int ClimberMotor           = 5;  // Climber motor
        public static int DrumMotor              = 10;  // Intakes drum motor
        public static int ShooterVerticalPitch   = 8;  // Shooter's vertical pitch
        public static int FeederMotor            = 11;  // Feeder motor
        public static int IntakeMotor            = 12; // Intake motor
        public static int ControlPanelMotor      = 9; // Control Panel rotator
        public static int TurretRotationMotor    = 7; // Turret rotation motor
        public static int PowerDistributionPanel = 30; // Power Distribution Panel (PDP)
    }

    // PCM module mapping
    public static class PCMChannels {
        public static int DriveShift1   = 2;
        public static int DriveShift2   = 3;
        public static int ControlPanel1 = 0;
        public static int ControlPanel2 = 1;
    }

    // PDP power mapping
    public static class PDPPorts {
        public static int DriveTrainMotor1 = 12;
        public static int DriveTrainMotor2 = 13;
        public static int DriveTrainMotor3 = 3;
        public static int DriveTrainMotor4 = 1;
        public static int ShooterMotor = 15;
        public static int ClimberMotor = 0;
        public static int DrumMotor = 9;
        public static int ShooterVerticalPitch = 11;
        public static int FeederMotor = 8;
        public static int IntakeMotor = 2;
        public static int ControlPanelMotor = 10;
        public static int TurretRotationMotor = 14;
    }
    
    // VISION
    public static class Vision {
        public static double kFieldGoalHeightFromGround = 98.0; // 31 inches used for testing
        public static double kLimelightHeightFromGround = 18.0; // vertical distance from limelight to ground
        public static double kLimelightMountingAngle    = 25.0;
    }

    // TurnTable
    public static class TurnTable {
        public static int MotorID  = CANIds.TurretRotationMotor;
        public static double speed = 1;
        public static double kP = 0.002;
        public static double kI = 0.0;
        public static double kD = 0.0001;
        public static double tolerance = 0;
        public static int LimitSwitch = 1;
    }

    // Shooter
    public static class Shooter {
        public static int ShootID = CANIds.ShooterMotor;
        public static double PIDp = 0.000018;
        public static double PIDi = 0.0;
        public static double PIDd = 0.0001;
        public static double PIDiz = 0.0;
        public static double MaxOutput = 1.0;
        public static double MinOutput = -1.0;
        public static double MaxRPM = 5200;
        public static int CurrentLimit = 38;
    }
   
    // Drum
    public static class Drum {
        public static int MotorID = CANIds.DrumMotor;
        public static double DrumDiameter   = 22.0; // in inches
        public static double DrumEncoderPPI = (4096 / (DrumDiameter * Math.PI)); // PPI
        public static int IRSensor = 1;
        public static int DrumLocationOffset = 10;
        public static int LimitSwitch = 2;
        public static double PositionOffset =  100.0;
        public static double kP = 0.05;
        public static double kI = 0;
        public static double kD = 0;
        public static double tolerance = 55.0;
        public static double speed = 1;
    }
    
    // Control Panel
    public static class ControlPanel {
        public static int PCM = CANIds.PCM;
        public static int PCMChannel1 = PCMChannels.ControlPanel1;
        public static int PCMChannel2 = PCMChannels.ControlPanel2;
        public static int MotorID = CANIds.ControlPanelMotor;
    }

    // DriveTrain
    public static class DriveTrain {
        public static int PCM = CANIds.PCM;
        public static int PCMChannel1 = PCMChannels.DriveShift1;
        public static int PCMChannel2 = PCMChannels.DriveShift2;
        public static int DriveTrainMotor1 = CANIds.DriveTrainMotor1;
        public static int DriveTrainMotor2 = CANIds.DriveTrainMotor2;
        public static int DriveTrainMotor3 = CANIds.DriveTrainMotor3;
        public static int DriveTrainMotor4 = CANIds.DriveTrainMotor4;
        public static int WheelDiameter = 0;
        public static int CurrentLimit = 38;
    }

    // Climber
    public static class Climber {
        public static int MotorID = CANIds.ClimberMotor;
        public static int UpwardRPM = -5800;
        public static int DownwardRPM = 5800;
        public static int CurrentLimit = 38;
    }

    // Color Sensor
    public static class ColorSensor {
        public static int SerialBaudRate = 9600; // 9600 or 115200 (source: https://www.chiefdelphi.com/t/roborio-and-serial-data/144312/2)
    }

    // Intake
    public static class Intake {
        public static int MotorID = CANIds.IntakeMotor;
        public static double InSpeed = 1; // percent output
        public static double OutSpeed = -1; // percent output
        public static double MaxCurrentDraw = 25; // in amps
        public static double MaxDrawTime = 1.0; // in seconds
        public static double ReverseTime = 2.0; // in seconds
        public static double BallDetectionVoltage = 1675;
    }

    // Feeder
    public static class Feeder {
        public static int MotorID = CANIds.FeederMotor;
        public static double Speed = 0.75;
    }

    // Hood
    public static class Hood {
        public static int MotorID = CANIds.ShooterVerticalPitch;
        public static int LimitSwitch = 3;
        public static double HomingSpeed = -0.3;
        public static double kP = 0.0005;
        public static double kI = 0.0;
        public static double kD = 0.0;
        public static double tolerance = 55.0;
    }
}