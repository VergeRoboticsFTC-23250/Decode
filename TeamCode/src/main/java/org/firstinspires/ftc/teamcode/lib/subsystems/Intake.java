package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Context;

public class Intake extends SubsystemBase {
    public MotorEx intake, transfer;
    public ServoEx intakeArm;

    public static double armUp = 0.92, armDown = 0.795, armJiggle = 0.815;

    public Intake(Context c) {
        intake = new MotorEx(c.hMap, "ehm3");
        transfer = new MotorEx(c.hMap, "ehm2");
        intakeArm = new ServoEx(c.hMap, "sh3");
    }

    public void armDown() {
        intakeArm.set(armDown);
    }
    public void armJiggle() {
        intakeArm.set(armJiggle);
    }
    public void armUp() {
        intakeArm.set(armUp);
    }
    public void set(double pow) {
        intake.set(pow);
        transfer.set(pow);
    }
    public void setIntake(double pow) {
        intake.set(pow);
    }
    public void setTransfer(double pow) {
        transfer.set(pow);
    }
}
