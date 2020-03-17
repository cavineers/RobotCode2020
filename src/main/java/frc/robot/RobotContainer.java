package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.Limelight;
import frc.robot.commands.AddToHood;
import frc.robot.commands.HomeAll;
import frc.robot.commands.RemoveFromHood;
import frc.robot.commands.ToggleCoastBrakeMode;
import frc.robot.commands.ToggleControlPanel;
import frc.robot.commands.ToggleGears;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleIntakePistons;
import frc.robot.commands.shoot.HighShooter;
import frc.robot.commands.shoot.LowShooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.CompressorController;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TurnTable;

public class RobotContainer {
    //* Driver Controller
    public Joystick joy = new Joystick(0);
    public JoystickButton a_button = new JoystickButton(joy, 1);
    public JoystickButton b_button = new JoystickButton(joy, 2);
    public JoystickButton x_button = new JoystickButton(joy, 3);
    public JoystickButton y_button = new JoystickButton(joy, 4);
    public JoystickButton l_bump = new JoystickButton(joy, 5);
    public JoystickButton r_bump = new JoystickButton(joy, 6);
    public JoystickButton left_menu = new JoystickButton(joy, 7);
    public JoystickButton right_menu = new JoystickButton(joy, 8);
    public JoystickButton left_stick = new JoystickButton(joy, 9);
    public JoystickButton right_stick = new JoystickButton(joy, 10);

    public int lastDpad = -1;
    public boolean lastRightTrig = false;
    public boolean lastLeftTrig = false;

    //* Manual Controller
    public Joystick manual_joy = new Joystick(1);
    public JoystickButton manual_a_button = new JoystickButton(manual_joy, 1);
    public JoystickButton manual_b_button = new JoystickButton(manual_joy, 2);
    public JoystickButton manual_x_button = new JoystickButton(manual_joy, 3);
    public JoystickButton manual_y_button = new JoystickButton(manual_joy, 4);
    public JoystickButton manual_l_bump = new JoystickButton(manual_joy, 5);
    public JoystickButton manual_r_bump = new JoystickButton(manual_joy, 6);
    public JoystickButton manual_left_menu = new JoystickButton(manual_joy, 7);
    public JoystickButton manual_right_menu = new JoystickButton(manual_joy, 8);
    public JoystickButton manual_left_stick = new JoystickButton(manual_joy, 9);
    public JoystickButton manual_right_stick = new JoystickButton(manual_joy, 10); 

    public int manual_lastDpad = -1;
    public boolean manual_lastRightTrig = false;
    public boolean manual_lastLeftTrig = false;

    public enum CONTROLLER_MODE {
        AUTO_SHOOT, CONTROL_P, CLIMB, NEUTRAL
    }

    public CONTROLLER_MODE currentTriggerSetting = CONTROLLER_MODE.NEUTRAL;

    private boolean manualShooter = false;

    //* Subsystems
    public PowerDistributionPanel PDP = new PowerDistributionPanel(Constants.CANIds.PowerDistributionPanel);
    public CompressorController compressor = new CompressorController(false);
    // public ColorSensor colorSensor = new ColorSensor(this.colorSensorNano);
    // public Arduino colorSensorNano = new Arduino(SerialPort.Port.kUSB1);
    public DriveTrain drivetrain = new DriveTrain(this.getJoystick());
    public ControlPanel controlPanel = new ControlPanel();
    public Dashboard dashboard = new Dashboard(this);
    public Limelight limelight = new Limelight();
    public Intake intake = new Intake(this);
    public Shooter shooter = new Shooter();
    public Climber climber = new Climber();
    public Feeder feeder = new Feeder();
    // public DumbDrum drum = new DumbDrum();
    public Drum drum = new Drum();
    public Hood hood = new Hood();
    public TurnTable turnTable = new TurnTable(this.limelight);
    public DankDash dank = new DankDash();

    /**
     * RobotContainer
     */
    public RobotContainer() {
        //! DISABLED COMPRESSOR FOR TESTING
        this.compressor.setMode(CompressorController.CompressorMode.DISABLED);
        this.compressor.setClosedLoop(false);

        // Config the controller
        configureButtonBindings();

        // Configure DANK 
        this.dank.setRobotName("Kronk").setProfileName("Kronk").export();
    }

    /**
     * configureButtonBindings
     */
    private void configureButtonBindings() {
        //! ACTUAL FINAL BUTTON CONFIGS

        //^ DriveTrain (Shifting)
        // left_stick.whenPressed(new ShiftGear(this.drivetrain, DriveTrain.DriveGear.LOW_GEAR));
        // right_stick.whenPressed(new ShiftGear(this.drivetrain, DriveTrain.DriveGear.HIGH_GEAR));
        left_stick.whenPressed(new ToggleGears(this.drivetrain));
        right_stick.whenPressed(new ToggleCoastBrakeMode(this.drivetrain));

        //^ Intake
        x_button.whenPressed(new ToggleIntake(this.intake));

        //^ Control Panel
        y_button.whenPressed(new ToggleControlPanel(this.controlPanel));

        //^ Shooting
        a_button.whenPressed(new HighShooter(this));

        b_button.whenPressed(new LowShooter(this));

        //^ Homing
        right_menu.whenPressed(new HomeAll(this));

        //^ Toggle intake pistons
        left_menu.whenPressed(new ToggleIntakePistons(this.intake));

        //! 2nd driver buttons
        manual_l_bump.whenPressed(new RemoveFromHood());
        manual_r_bump.whenPressed(new AddToHood());
    }

    /**
     * updateController
     * Periodic to update controller
     */
    public void updateController() {
        //^ Climber Code
        if (this.l_bump.get()) {
            this.climber.climberMotor.set(-1);
        } else
        if (this.r_bump.get()) {
            this.climber.climberMotor.set(1);
        } else {
            this.climber.climberMotor.set(0);
        }

        //^ CP Code
        if (this.joy.getRawAxis(2) > 0.1) {
            this.controlPanel.controlMotor.set(-this.joy.getRawAxis(2));
        } else
        if (this.joy.getRawAxis(3) > 0.1) {
            this.controlPanel.controlMotor.set(this.joy.getRawAxis(3));
        } else {
            this.controlPanel.controlMotor.set(0);
        }
   
        //^ D-Pad
        if (lastDpad != joy.getPOV()) {
            switch (joy.getPOV()) {
            case 0:
                break;
            case 90:
                this.drum.moveToNext();
                this.drum.addBall();
                break;
            case 180:
                if (this.intake.getMotorState() != Intake.IntakeMotorState.OFF) {
                    this.intake.setMotorState(Intake.IntakeMotorState.OFF);
                } else {
                    this.intake.setMotorState(Intake.IntakeMotorState.OOF);
                }
                break;
            case 270:
                this.drum.moveBack();
                this.drum.removeBall();
                break;
            default:
                break;
            }
        }
        lastDpad = joy.getPOV();

        //^ Manual user
        if (manual_lastDpad != manual_joy.getPOV()) {
            switch (manual_joy.getPOV()) {
                case 0:
                    if (this.shooter.getCurrentMode() == Shooter.ShooterMode.DISABLED) {
                        this.shooter.enable();
                        this.manualShooter = true;
                    } else {
                        this.shooter.disable();
                        this.manualShooter = false;
                    }
                    break;
                case 90:
                    if (this.turnTable.getState() == TurnTable.TurnTableState.TARGETING) {
                        this.turnTable.setState(TurnTable.TurnTableState.NEUTRAL);
                    } else {
                        this.turnTable.setState(TurnTable.TurnTableState.TARGETING);
                    }
                    break;
                case 180:
                    if (this.hood.isEnabled()) {
                        this.hood.disable();
                    } else {
                        this.hood.enable();
                    }
                    break;
                case 270:
                    if (this.drum.isEnabled()) {
                        this.drum.disable();
                    } else {
                        this.drum.enable();
                    }
                    break;
                default:
                    break;
            }
        }
        manual_lastDpad = manual_joy.getPOV();

        //^ Manual Shooter Code
        if (this.manualShooter) {
            if (this.manual_joy.getRawAxis(3) > 0.1) {
                SmartDashboard.putNumber("shooter_speed", this.manual_joy.getRawAxis(3)*5500);
            } else {
                SmartDashboard.putNumber("shooter_speed", 0);
            }
        }

        if (!this.drum.isEnabled()) {
            this.drum.motor.set(this.manual_joy.getRawAxis(0));
        }
    }

    /**
     * getJoystick
     * @return returns the joystick
     */
    public Joystick getJoystick() {
        return joy;
    }

    /**
     * get the current power draw of pdp port x
     * @param port pdp port
     * @return the current draw (in amps)
     */
    public double getCurrentDrawOfPort(int port) {
        // return this.PDP.getCurrent(port);
        return 0.0;
    }

    /**
     * Get the wanted control panel color
     * @return get color given by FMS
     */
    public ColorSensor.ControlPanelColor getControlPanelColor() {
        String msg = DriverStation.getInstance().getGameSpecificMessage();
        if (msg.length()>0) {
            switch (msg) {
                case "R":
                case "r":
                    return ColorSensor.ControlPanelColor.RED;
                case "G":
                case "g":
                    return ColorSensor.ControlPanelColor.GREEN;
                case "B":
                case "b":
                    return ColorSensor.ControlPanelColor.BLUE;
                case "Y":
                case "y":
                    return ColorSensor.ControlPanelColor.YELLOW;
                default:
                    return ColorSensor.ControlPanelColor.UNKNOWN;
            }
        } else {
            return ColorSensor.ControlPanelColor.UNKNOWN;
        }
    }
}
