package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Context;

public class Turret extends SubsystemBase {

    public ServoEx servo0;
    public ServoEx servo2;
    public ServoEx servo3;
    public static final double servoRange = 383.5184;

    public Turret(Context c) {
        servo0 = new ServoEx(c.hMap, "chs0", servoRange);
        servo2 = new ServoEx(c.hMap, "chs2", servoRange);
        servo3 = new ServoEx(c.hMap, "chs3", servoRange);
    }

    public void setAngle(double angle) {
        servo0.set(angle);
        servo2.set(angle);
        servo3.set(angle);
    }
}