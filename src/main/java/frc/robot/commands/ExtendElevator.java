/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendElevator extends CommandBase {
    private Climber climber;

    public ExtendElevator(Climber climber) {
        addRequirements(climber);
        this.climber = climber;
    }

    @Override
    public void initialize() {
        this.climber.setMode(Climber.ClimberMode.EXTENDING);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
