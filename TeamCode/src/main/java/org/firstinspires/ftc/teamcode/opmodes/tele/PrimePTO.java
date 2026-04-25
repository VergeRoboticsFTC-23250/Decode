package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.subsystems.Drivetrain;

@TeleOp
public class PrimePTO extends OpMode {

    ServoEx pto;

    @Override
    public void init() {
        pto = new ServoEx(hardwareMap, "sh5");
    }

    @Override
    public void loop() {
        pto.set(Drivetrain.disengage);
        telemetry.addLine("Pull the plates apart in the PTO!");
    }
}
