/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //? All constants begin with a lowercase 'k'

    // CAN network mapping
    public static int kDriveTrainMotor1CANid       = 4;  // Drive train's first motor
    public static int kDriveTrainMotor2CANid       = 3;  // Drive train's second motor
    public static int kDriveTrainMotor3CANid       = 1;  // Drive train's third motor
    public static int kDriveTrainMotor4CANid       = 2;  // Drive train's fourth motor
    public static final double kDriveWheelDiameter = 6; // in inches
    public static final double kDriveSensorUnitsPerInch = 4096 / (kDriveWheelDiameter * Math.PI);
    public static final int kTimeoutMs = 10;
    public static final int kPIDLoopIdx = 0;
    public static final double kDefaultDt = 0.01;
    public static final double kAVelocity = 0.001;
    public static final double kDriveVoltageRampRate = 0;
  
    public static int kTurretRotationMotorCANid    = 12;  // Turret rotation motor
    public static int kFlyWheelMotorCANid          = 5;  // The shooter's fly wheel motor
    public static int kIntakeMotorCANid            = 10;  // Intake motor
    public static int kClimberMotorCANid           = 6;  // Climber motor
    public static int kControlPanelMotorCANid      = 11;  // Control Panel rotator
    public static int kShooterVerticalPitchCANid   = 8;  // Shooter's vertical pitch
    public static int kPneumaticsControlCANid      = 20; // Pneumatic Control Module (PCM)
    public static int kPowerDistributionPanelCANid = 30; // Power distribution panel (PDP)

    public static int kDriveShifter1 = 3;
    public static int kDriveShifter2 = 4;
}