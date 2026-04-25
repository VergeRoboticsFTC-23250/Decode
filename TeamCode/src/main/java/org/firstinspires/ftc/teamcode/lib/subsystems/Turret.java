package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Context;

public class Turret extends SubsystemBase {

    public ServoEx turret1;
    public ServoEx turret2;
    public ServoEx turret3;
    public static final double servoRange = 383.5184;

    public Turret(Context c) {
        turret1 = new ServoEx(c.hMap, "sh0", servoRange);
        turret2 = new ServoEx(c.hMap, "sh1", servoRange);
        turret3 = new ServoEx(c.hMap, "sh2", servoRange);
    }

    public void setAngle(double angle) {
        turret1.set(angle);
        turret2.set(angle);
        turret3.set(angle);
    }
}