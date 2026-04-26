package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Globals;
import org.firstinspires.ftc.teamcode.lib.util.Helpers;

@Config
public class Turret extends SubsystemBase {
    private Globals g;
    public ServoEx turret1;
    public ServoEx turret2;
    public ServoEx turret3;
    public static final double servoRange = 383.5184;
    public static double offset = -6;

    public Turret(Globals g) {
        this.g = g;
        turret1 = new ServoEx(g.hMap, "sh0", servoRange);
        turret2 = new ServoEx(g.hMap, "sh1", servoRange);
        turret3 = new ServoEx(g.hMap, "sh2", servoRange);
    }

    public void setAngle(double radians) {
        double degrees = Math.toDegrees(Helpers.wrap(radians + Math.toRadians(offset)));
        turret1.set(degrees);
        turret2.set(degrees);
        turret3.set(degrees);
    }
}