package frc.lib.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.lib.AutoAction;

public class Wait extends AutoAction {
    private int time;

    public Wait(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        Timer.delay(this.time);
    }
}