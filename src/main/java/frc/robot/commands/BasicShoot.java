// package frc.robot.commands;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.Feeder;

// public class BasicShoot extends CommandBase {
//     // Local RobotContainer
//     private RobotContainer rc;

//     // Finished
//     private boolean finished = false;

//     // State
//     private enum Stage {
//         INITIAL,
//         TUNING_TURNTABLE,
//         SHOOTING
//     }

//     // Current state
//     private Stage currentStage;

//     // step
//     private int step = -1;

//     // shoot time elapsed
//     private double timeElapsed;

//     // set feeder state
//     private boolean setFeederState = false;
    
//     public BasicShoot() {
//         // Start shooter
//         this.rc.shooter.enable();

//         // Stage is initial
//         this.currentStage = Stage.INITIAL;

//         // Move turntable
//         new TurntableToTarget(this.rc.turnTable, this.rc.limelight.getHorizontalOffset());
//         this.timeElapsed = Timer.getFPGATimestamp();
//         this.currentStage = Stage.TUNING_TURNTABLE;
//     }

//     @Override
//     public void initialize() {}

//     @Override
//     public void execute() {
//         if (this.currentStage == Stage.TUNING_TURNTABLE) {
//             if (Timer.getFPGATimestamp()-this.timeElapsed > 2.0) {
//                 this.step = 0;
//                 this.currentStage = Stage.SHOOTING;
//             }
//         }
//         if (this.currentStage == Stage.SHOOTING) {
//             if (this.step == 0) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.step++;
//                 }
//             }
//             if (this.step == 1) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.step++;
//                 }
//             }
//             if (this.step == 2) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.step++;
//                 }
//             }
//             if (this.step == 3) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.step++;
//                 }
//             }
//             if (this.step == 4) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.step++;
//                 }
//             }
//             if (this.step == 5) { 
//                 if (!this.setFeederState) {
//                     this.rc.feeder.setState(Feeder.FeederState.ENABLED);
//                     this.setFeederState = true;
//                     this.timeElapsed = Timer.getFPGATimestamp();
//                 }
                
//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 0.75) {
//                     this.rc.feeder.setState(Feeder.FeederState.DISABLED);
//                     this.rc.drum.rotateNext();
//                 }

//                 if (Timer.getFPGATimestamp()-this.timeElapsed > 1.2) {
//                     this.rc.shooter.disable();
//                     this.finished = true;
//                 }
//             }
//         }
//     }

//     @Override
//     public void end(boolean interrupted) {}

//     @Override
//     public boolean isFinished() {
//         return this.finished;
//     }
// }
