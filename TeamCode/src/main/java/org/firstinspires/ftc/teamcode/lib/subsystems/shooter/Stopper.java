package org.firstinspires.ftc.teamcode.lib.subsystems.shooter;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Stopper extends SubsystemBase {
    private final ServoEx stopper;
    public static double close = 0.38, open = .535;

    public Stopper(Globals g){
        stopper = new ServoEx(g.hMap, "ehs0");
        close();
    }

    public void close(){
        stopper.set(close);
    }

    public void open(){
        stopper.set(open);
    }

    public void toggle() {
        if (stopper.get() == open) {
            close();
        } else {
            open();
        }
    }
}
