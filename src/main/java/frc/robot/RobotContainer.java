package frc.robot;

<<<<<<< Updated upstream
=======
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
>>>>>>> Stashed changes
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.Limelight;
import frc.robot.subsystems.ColorSensor;
import frc.robot.commands.ExtendControlPanel;
import frc.robot.commands.RetractControlPanel;
import frc.robot.commands.ShiftGear;
import frc.robot.commands.StartSpinning;
import frc.robot.commands.StopSpinning;
import frc.robot.commands.TeleopDrive;
<<<<<<< Updated upstream
=======
import frc.robot.commands.TurnTableToAngle;
import frc.robot.commands.EnableLED;
import frc.robot.commands.DisableLED;
>>>>>>> Stashed changes
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;
<<<<<<< Updated upstream
import frc.robot.subsystems.CompressorController;
=======
>>>>>>> Stashed changes
import frc.robot.subsystems.Arduino;

public class RobotContainer {
    // Controller
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

    public enum CONTROLLER_MODE {
        AUTO_SHOOT, CONTROL_P, CLIMB, NEUTRAL
    }

    public CONTROLLER_MODE currentTriggerSetting = CONTROLLER_MODE.NEUTRAL;

<<<<<<< Updated upstream
    // Subsystems
    // public DriveTrain drivetrain = new DriveTrain(this.getJoystick());
    // public Turntable turnTable = new Turntable();
    // public Limelight limelight = new Limelight();
    // public Shooter shooter = new Shooter();
    // public Climber climber = new Climber();
    // public ControlPanel controlPanel = new ControlPanel();
    // public PowerDistributionPanel PDP = new
    // PowerDistributionPanel(Constants.CANIds.PowerDistributionPanel);
    // public Dashboard dashboard = new Dashboard(this);
    public Arduino arduino = new Arduino();
    // public ColorSensor colorSensor = new ColorSensor();
    // public CompressorController compressor = new CompressorController(true);
=======
    //* Subsystems
    // public PowerDistributionPanel PDP = new PowerDistributionPanel(Constants.CANIds.PowerDistributionPanel);
    // public CompressorController compressor = new CompressorController(false);
    // public ColorSensor colorSensor = new ColorSensor(this.colorSensorNano);
    public Arduino colorSensorNano = new Arduino(SerialPort.Port.kUSB1);
    // public DriveTrain drivetrain = new DriveTrain(this.getJoystick());
    // public ControlPanel controlPanel = new ControlPanel();
    // public Dashboard dashboard = new Dashboard(this);
    // public Turntable turnTable = new Turntable();
    // public Limelight limelight = new Limelight();
    // public Intake intake = new Intake(this);
    // public Shooter shooter = new Shooter();
    // public Climber climber = new Climber();
    // public Feeder feeder = new Feeder();
    // public Drum drum = new Drum();
    // public Hood hood = new Hood();
>>>>>>> Stashed changes

    /**
     * RobotContainer
     */
    public RobotContainer() {
<<<<<<< Updated upstream
        configureButtonBindings(); // Config Buttons
=======
        //^ Turn of the compressor during just motor testing
        // this.compressor.setClosedLoop(false);
        // this.compressor.setMode(CompressorMode.DISABLED);

        // Config the controller
        configureButtonBindings();
>>>>>>> Stashed changes
    }

    /**
     * configureButtonBindings
     */
    private void configureButtonBindings() {
        // ! TESTING BUTTON CONFIGS

        // ^ Elevator
        // y_button.whenPressed(new ExtendElevator(this.climber));
        // b_button.whenPressed(new RetractElevator(this.climber));
        // a_button.whenPressed(new StopElevator(this.climber));

        // ^ Control Panel
<<<<<<< Updated upstream
        /*
         * b_button.whenPressed(new StartSpinning(this.controlPanel));
         * x_button.whenPressed(new StopSpinning(this.controlPanel));
         * y_button.whenPressed(new ExtendControlPanel(this.controlPanel));
         * a_button.whenPressed(new RetractControlPanel(this.controlPanel));
         */
        // ^ Vision
        // a_button.whenPressed(new AutoAlign(this.drivetrain, this.turnTable,
        // this.limelight));
=======
        // b_button.whenPressed(new StartSpinning(this.controlPanel));
        // x_button.whenPressed(new StopSpinning(this.controlPanel));
        // y_button.whenPressed(new ExtendControlPanel(this.controlPanel));
        // a_button.whenPressed(new RetractControlPanel(this.controlPanel));
        a_button.whenPressed(new EnableLED(this.colorSensorNano));
        b_button.whenPressed(new DisableLED(this.colorSensorNano));

        //^ Vision
        // a_button.whenPressed(new AutoAlign(this.drivetrain, this.turnTable, this.limelight));
>>>>>>> Stashed changes
        // a_button.whenPressed(new TurntableToTarget(this.turnTable, 90));
        // a_button.whenPressed(new TurntableToTarget(this.turnTable,
        // this.limelight.getHorizontalOffset()));
        // b_button.whenPressed(new StopTurntable(this.turnTable));

<<<<<<< Updated upstream
        // ! ACTUAL FINAL BUTTON CONFIGS

        // ^ DriveTrain (Shifting)
        // left_stick.whenPressed(new ShiftGear(this.drivetrain,
        // DriveTrain.DriveGear.HIGH_GEAR));
        // right_stick.whenPressed(new ShiftGear(this.drivetrain,
        // DriveTrain.DriveGear.LOW_GEAR));
=======
        //^ Shooty Things
        // a_button.whenPressed(new Shoot(this.limelight, this.shooter));
        // a_button.whenPressed(new ShooterOn(this.shooter));
        // b_button.whenPressed(new ShooterOff(this.shooter));

        //^ Intake
        // a_button.whenPressed(new IntakeOn(this.intake));
        // b_button.whenPressed(new IntakeOff(this.intake));

        //^ Feeder
        // r_bump.whenPressed(new FeederOn(this.feeder));
        // l_bump.whenPressed(new FeederOff(this.feeder));
        
        //^ Drum
        // x_button.whenPressed(new DrumRotateNext(this.drum));

        //^ Hood
        // right_menu.whenPressed(new HomeHood(this.hood));
        // y_button.whenPressed(new HoodToAngle(this.hood, 20*(4096/360)));

        //^ Turntable
        // b_button.whenPressed(new TurntableToTarget(this.turnTable, this.limelight));
        // x_button.whenPressed(new TurnTableToAngle(this.turnTable, this.limelight));

        //! ACTUAL FINAL BUTTON CONFIGS

        //^ DriveTrain (Shifting)
        // left_stick.whenPressed(new ShiftGear(this.drivetrain, DriveTrain.DriveGear.LOW_GEAR));
        // right_stick.whenPressed(new ShiftGear(this.drivetrain, DriveTrain.DriveGear.HIGH_GEAR));

        // left_menu.whenPressed(new HomeTurntable(this.turnTable));
>>>>>>> Stashed changes
    }

    /**
     * isRightTriggerPressed
     * 
     * @return if it's pressed
     */
    private boolean isRightTriggerPressed() {
        final double rightTrig = this.getJoystick().getRawAxis(3);
        return rightTrig > 0.9;
    }

    /**
     * isLeftTriggerPressed
     * 
     * @return if it's pressed
     */
    private boolean isLeftTriggerPressed() {
        final double leftTrig = this.getJoystick().getRawAxis(2);
        return leftTrig > 0.9;
    }

    /**
     * updateController Periodic to update controller
     */
    public void updateController() {

        // new TurntableToTarget(this.turnTable, this.limelight.getHorizontalOffset());
        if (lastRightTrig != isRightTriggerPressed()) {
            // the right trigger changed state
            lastRightTrig = isRightTriggerPressed();
            if (lastRightTrig && currentTriggerSetting == CONTROLLER_MODE.CONTROL_P) {
                // the right trigger is pressed and we are in Control Panel mode

            } else if (lastRightTrig && currentTriggerSetting == CONTROLLER_MODE.CLIMB) {
                // the right trigger is pressed and we are in Climb mode
            } else if (lastRightTrig && currentTriggerSetting == CONTROLLER_MODE.NEUTRAL) {
                // the right trigger is pressed and we are in Neutral

            } else if (lastRightTrig && currentTriggerSetting == CONTROLLER_MODE.AUTO_SHOOT) {
                // the right trigger is pressed and we are in Auto Shoot mode

            }

        }

        if (lastLeftTrig != isLeftTriggerPressed()) {
            // the left trigger changed state
            lastLeftTrig = isLeftTriggerPressed();
            if (lastLeftTrig && currentTriggerSetting == CONTROLLER_MODE.CONTROL_P) {
                // the left trigger is pressed and we are in Control Panel mode
            } else if (lastLeftTrig && currentTriggerSetting == CONTROLLER_MODE.CLIMB) {
                // the left trigger is pressed and we are in Climb mode
            } else if (lastLeftTrig && currentTriggerSetting == CONTROLLER_MODE.NEUTRAL) {
                // the left trigger is pressed and we are in Neutral mode
            } else if (lastLeftTrig && currentTriggerSetting == CONTROLLER_MODE.AUTO_SHOOT) {
                // the left trigger is pressed and we are in Auto Shoot mode
            }
        }

        if (lastDpad != joy.getPOV()) {
            switch (joy.getPOV()) {
            case 0:
                // Top
                // currentTriggerSetting = CONTROLLER_MODE.CONTROL_P;
                System.out.println("In Control Panel mode");
                // this.compressor.setMode(CompressorController.CompressorMode.ENABLED);
<<<<<<< Updated upstream
=======
                // this.hood.enable();
                // new IntakeOn(this.intake);
>>>>>>> Stashed changes
                break;
            case 90:
                // Right
                currentTriggerSetting = CONTROLLER_MODE.CLIMB;
                System.out.println("In Climb mode");
                break;
            case 180:
                // Bottom
                // currentTriggerSetting = CONTROLLER_MODE.NEUTRAL;
                System.out.println("In Neutral mode");
                // this.compressor.setMode(CompressorController.CompressorMode.DISABLED);
<<<<<<< Updated upstream
=======
                // this.hood.disable();
>>>>>>> Stashed changes
                break;
            case 270:
                currentTriggerSetting = CONTROLLER_MODE.AUTO_SHOOT;
                System.out.println("In Auto Shoot mode");
                break;
            default:
                System.out.println("Nothing is pressed, hopefully");
                break;
            }
        }
        lastDpad = joy.getPOV();
    }

    // Tele init
    public void teleInit() {
        // new TeleopDrive(this.drivetrain, this.joy);
    }

    /**
     * getJoystick
     * 
     * @return returns the joystick
     */
    public Joystick getJoystick() {
        return joy;
    }
}
