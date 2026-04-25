package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.lib.Snoopy;

@TeleOp(name = "TeleOp")
public class Tele extends CommandOpMode {
    Snoopy snoopy;
    @Override
    public void initialize() {
        snoopy = new Snoopy(hardwareMap);
    }

    @Override
    public void run() {
        super.run();
        snoopy.intake.run(gamepad1.right_trigger - gamepad1.left_trigger);
    }
}
