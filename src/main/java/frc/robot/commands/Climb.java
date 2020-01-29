package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
    private Climber climber;
    private RobotContainer robotContainer;

    public Climb (Climber climber, RobotContainer robotContainer) {
        this.climber = climber;
        this.robotContainer = robotContainer;
    }


    @Override
    public void initialize(){
    }

    @Override
    public void execute() {
        if (climber.getClimberState(robotContainer) == Climber.ClimberState.ON) {
            climber.getWinchMotor().set(0.8);
        } else if (climber.getClimberState(robotContainer) == Climber.ClimberState.OFF){
            climber.getWinchMotor().set(0);
        }
    }

}