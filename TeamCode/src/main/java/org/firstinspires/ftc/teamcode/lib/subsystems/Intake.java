package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Intake extends SubsystemBase {
    private final MotorEx intake, transfer;
    private final ServoEx pivot;
    private final Brushland front, middle, back;

    public static double raise = 0.92, lower = 0.795;
    private double pow;

    public Intake(Globals g) {
        intake = new MotorEx(g.hMap, "ehm3");
        transfer = new MotorEx(g.hMap, "ehm2");
        pivot = new ServoEx(g.hMap, "sh3");

        front = new Brushland(g.hMap, "dpin4", "dpin5");
        middle = new Brushland(g.hMap, "dpin2", "dpin3");
        back = new Brushland(g.hMap, "dpin0", "dpin1");
    }
    public void run(double pow) {this.pow = pow;}

    @Override
    public void periodic() {
        super.periodic();

        pivot.set(pow > 0? raise : lower);

        if(pow > 0){
            if (!back.isBlocked()){
                transfer.set(pow);
            }

            if(!middle.isBlocked() || !front.isBlocked()){
                intake.set(pow);
            }
        }else{
            transfer.set(pow);
            intake.set(pow);
        }
    }
}
