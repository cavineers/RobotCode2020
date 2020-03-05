package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class TeleopClimb extends CommandBase {
    private Climber climber;

    private Joystick joy;

    public TeleopClimb(Climber climber, Joystick joy) {
        addRequirements(climber);
        this.climber = climber;
        this.joy = joy;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (this.joy.getRawAxis(2) > 0.1) {
            this.climber.manual(this.joy.getRawAxis(2));
        } else 
        if (this.joy.getRawAxis(3) > 0.1) {
            this.climber.manual(-this.joy.getRawAxis(3));
        } else {
            this.climber.manual(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
