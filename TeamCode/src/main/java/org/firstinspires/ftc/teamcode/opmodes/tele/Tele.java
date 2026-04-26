package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.Snoopy;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@TeleOp(name = "TeleOp")
public class Tele extends CommandOpMode {
    Globals g;
    Snoopy snoopy;
    @Override
    public void initialize() {
        g = new Globals(this);
        snoopy = new Snoopy(this);
    }

    @Override
    public void run() {
        super.run();
        snoopy.run();
        snoopy.intake.run(gamepad1.right_trigger - gamepad1.left_trigger);
        snoopy.drivetrain.drive(gamepad1);
    }
}
