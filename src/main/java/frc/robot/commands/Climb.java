package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
    private Climber climber;
    private RobotContainer robotContainer;

    public Climb (Climber climber, RobotContainer robotContainer) {
        addRequirements(climber);
        this.climber = climber;
        this.robotContainer = robotContainer;
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute() {
        if (climber.getClimberState(robotContainer) == Climber.ClimberState.UP) {
            climber.getWinchMotor().set(-5800);
        } else if (climber.getClimberState(robotContainer) == Climber.ClimberState.OFF){
            climber.getWinchMotor().set(0);
        } else if (climber.getClimberState(robotContainer) == Climber.ClimberState.DOWN){
            climber.getWinchMotor().set(5800);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override 
    public boolean isFinished(){
        return true;
    }

}