package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TeleOp extends CommandOpMode {

    Turret turret;
    Drivetrain drivetrain;
    Gamepad old;
    public static double testPanels = 0;

    @Override
    public void initialize() {
        turret = new Turret(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        register(turret, drivetrain);
        old = gamepad1;
//        turret.setTarget(90);
    }

    public void run() {

        drivetrain.robotCentric(gamepad1);
        turret.updateFacingPoint(15,15, drivetrain.follower.getPose());

        telemetry.addData("heading", drivetrain.getHeading());
        telemetry.addData("setpoint", turret.getTarget());
        telemetry.addData("position", turret.turret.getCurrentPosition() * turret.degreesPerTick);
        telemetry.addData("left stick x", gamepad1.left_stick_x);
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        telemetry.addData("right stick x", gamepad1.right_stick_x);
        telemetry.addData("testPanels", testPanels);
        telemetry.update();

        old = gamepad1;
    }


}