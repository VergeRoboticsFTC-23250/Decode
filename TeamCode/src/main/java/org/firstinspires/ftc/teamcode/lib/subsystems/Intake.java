package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Intake extends SubsystemBase {
    public static double raise = 0.92, lower = 0.795;
    private boolean enableTransfer = false;

    private final MotorEx intake, transfer;
    private final ServoEx pivot;

    public Intake(Globals g) {
        intake = new MotorEx(g.hMap, "ehm3");
        transfer = new MotorEx(g.hMap, "ehm2");
        pivot = new ServoEx(g.hMap, "sh3");

        run(0);
    }
    public void run(double pow) {
        pivot.set(pow > 0? lower : raise);
        intake.set(pow);
        transfer.set(enableTransfer? pow : 0);
    }
    public void enableTransfer() {
        enableTransfer = true;
        transfer.set(intake.get());
    }

    public void disableTransfer() {
        enableTransfer = false;
        transfer.set(0);
    }
}
