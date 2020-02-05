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
        climber.getWinchMotor().set(climber.getWinchMotorSpeed(robotContainer));
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override 
    public boolean isFinished(){
        return true;
    }

}