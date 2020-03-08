package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.ColorSensor;

public class ToggleControlPanel extends CommandBase {
    private ControlPanel cp;
    private Arduino arduino;
    private ColorSensor colorSensor;

    private int cycles = 0;
    private int segment = 0;

    private String CurrentColor = "blank";
    private String PreviousColor = "blank";
    private String StartingColor = "blank";
    /**
     * constructor
     */
    public ToggleControlPanel(ControlPanel cp, Arduino arduino, ColorSensor colorSensor) {
        addRequirements(cp);
        this.cp = cp;
        addRequirements(arduino);
        this.arduino = arduino;
        addRequirements(colorSensor);
        this.colorSensor = colorSensor;
    }

    private void WaitUntilCommand(boolean detectsColor) {
        while(detectsColor != true){
            // Color Sensor should not be over the Control Panel
            detectsColor = this.arduino.detectsColor();
        }
    }

    @Override
    public void initialize() {
        if (this.cp.getPistonPosition() == ControlPanel.ControlPanelPosition.RETRACTED) {
            this.cp.extend();
            this.arduino.enableLED();
            WaitUntilCommand(this.arduino.detectsColor());
            spinUntil();
            if (this.arduino.detectsColor() == false){
                this.cp.setSpin(0.0);
            } 
        } else {
            this.cp.setSpin(0.0);
            if(this.arduino.detectsColor() == true) {
                while(this.arduino.detectsColor() == true) {
                    this.arduino.detectsColor();
                }
            }
            else{
            this.cp.retract();
            this.arduino.disableLED();
            }
        }
    }
/* psuedocode
    Spin 3 - 5  times the Control panel
    steps 1. Get color from ColorSensor
    Steps 2. Declare and Init (CurrentColor versus PreviousColor) and starting color
      2a. CurrentColor = StartingColor;
      2b. if(CurrentColor != PreviousColor)
    Steps 3. Spin Motor and CountColors
*/

    /**
     * Will spin the ControlPanelMotor -
     * 
     */
    public void spinUntil(){
        cycles++;
        if(cycles == 1) {
        while(this.arduino.detectsColor() == true){
            if(CurrentColor == "Blank"){
                StartingColor = this.arduino.getColor();
                CurrentColor = StartingColor;
                PreviousColor = CurrentColor;
            }
            CurrentColor = this.arduino.getColor();
            if(CurrentColor != PreviousColor && segment < 24){
                segment++;
                PreviousColor = CurrentColor;
                this.cp.setSpin(1.0);
            } else if (CurrentColor != PreviousColor && segment >= 24 && segment <= 26){
                this.cp.setSpin(.8);
                segment++;
                PreviousColor = CurrentColor;
            } else if (CurrentColor != PreviousColor && segment >= 27 && segment <= 29) {
                this.cp.setSpin(.6);
                segment++;
                PreviousColor = CurrentColor;
            } else if (CurrentColor != PreviousColor && segment >= 30 && segment <=32) {
                this.cp.setSpin(0.0);
            } else {
                this.cp.setSpin(1.0);
            }
        }
    } else if (cycles == 2) {
        while(this.arduino.detectsColor() == true){
            
            this.arduino.detectsColor();  
        }
        
    } else {
        System.out.println("Whoops, that was not suppose to happen.");
        cycles = 0;
    }
    CurrentColor = "Blank";
    StartingColor = "Blank";
    PreviousColor = "Blank";
    this.cp.setSpin(0.0);
    }
    

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}