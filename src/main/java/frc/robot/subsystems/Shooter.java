package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import frc.robot.Constants;

public class Shooter extends PIDSubsystem {
    // Fly Wheel
    private final PWMVictorSPX flyWheel = new PWMVictorSPX(Constants.kFlyWheelMotorCANid);

    // Feeder
    private final PWMVictorSPX feeder = new PWMVictorSPX(Constants.kFeederMotorCANid);
    private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(Constants.kFeederVolts, Constants.kVVoltSecondsPerRotation);

    // Fly Wheel Encoder
    private final Encoder flyWheelEncoder = new Encoder(Constants.kShooterEncoderPorts[0], Constants.kShooterEncoderPorts[1], false);

    // Feeder

  public Shooter() {
    super(new PIDController(Constants.kShooterMotorPIDp, Constants.kShooterMotorPIDi, Constants.kShooterMotorPIDd));
    getController().setTolerance(Constants.kShooterToleranceRPS);
    flyWheelEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
    setSetpoint(Constants.kShooterTargetRPS);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    flyWheel.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return flyWheelEncoder.getRate();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void runFeeder() {
    feeder.set(Constants.kFeederSpeed);
  }

  public void stopFeeder() {
    feeder.set(0);
  }
}