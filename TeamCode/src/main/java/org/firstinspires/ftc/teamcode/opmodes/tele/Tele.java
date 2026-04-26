package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.Snoopy;

@TeleOp(name = "TeleOp")
public class Tele extends CommandOpMode {
    Snoopy snoopy;
    @Override
    public void initialize() {
        snoopy = new Snoopy(hardwareMap);
        ServoEx stopper = new ServoEx(hardwareMap, "ehs0");
        stopper.set(.38);
    }

    @Override
    public void run() {
        super.run();
        snoopy.intake.run(gamepad1.right_trigger - gamepad1.left_trigger);

        snoopy.drivetrain.drive(gamepad1);

        telemetry.update();
    }
}
