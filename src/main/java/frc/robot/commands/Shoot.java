package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Limelight;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
    private Limelight ll;
    private Shooter st;

    private NetworkTable table = NetworkTableInstance.getDefault().getTable("ShooterTrainer");
    
    private boolean gottenSpeed = false;

    public Shoot(Limelight ll, Shooter st) {
        this.ll = ll;
        this.st = st;
        addRequirements(st);
    }

    @Override
    public void initialize() {
        System.out.println(this.ll.getDistance());
        this.table.getEntry("data_in").setString("[]");
        this.table.getEntry("data_out").setString("{\"tx\": \""+this.ll.getHorizontalOffset()+"\", \"ty\": \""+this.ll.getVerticalOffset()+"\", \"distance\": \""+this.ll.getDistance()+"\"}");
    }

    @Override
    public void execute() {
        if (!this.gottenSpeed) {
            String val = this.table.getEntry("data_in").getString("[]");
            if (val != "[]") {
                this.st.setSpeed(Double.parseDouble(val));
                this.st.enable();
                this.gottenSpeed = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}