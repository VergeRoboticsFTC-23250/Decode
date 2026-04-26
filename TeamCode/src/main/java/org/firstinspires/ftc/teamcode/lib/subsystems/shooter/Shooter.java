package org.firstinspires.ftc.teamcode.lib.subsystems.shooter;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.lib.util.Globals;
@Config
public class Shooter extends SubsystemBase {
    Globals g;
    public Flywheel flywheel;
    public Hood hood;
    public Stopper stopper;

    public static double[] distance = {50, 70, 100, 120, 140, 160};
    public static double[] velocity = {1000, 1000, 1000, 1000, 1000, 1000};
    public static double[] percent = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
    public static boolean tuning = false;
    public static double velocityTuning = 0;
    public static double percentTuning = 0;

    private InterpLUT velocityLUT;
    private InterpLUT percentLUT;
    public Shooter(Globals g){
        this.g = g;
        flywheel = new Flywheel(g);
        hood = new Hood(g);
        stopper = new Stopper(g);

        velocityLUT = new InterpLUT();
        percentLUT = new InterpLUT();

        for (int i = 0; i < distance.length; i++) {
            velocityLUT.add(distance[i], velocity[i]);
        }
        for (int i = 0; i < distance.length; i++) {
            percentLUT.add(distance[i], percent[i]);
        }

        velocityLUT.createLUT();
        percentLUT.createLUT();
    }

    public void update(double dist){
        double clamped = Math.min(Math.max(dist, distance[0]), distance[distance.length - 1]);
        if(tuning){
            flywheel.set(velocityTuning);
            hood.set(percentTuning);
        }else{
            flywheel.set(velocityLUT.get(clamped));
            hood.set(percentLUT.get(clamped));
        }

        g.telemetry.addData("dist", dist);
    }
}
