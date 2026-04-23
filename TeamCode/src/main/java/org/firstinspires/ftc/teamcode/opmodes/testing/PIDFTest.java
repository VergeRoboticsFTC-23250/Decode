package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@Config
@TeleOp
public class PIDFTest extends OpMode {

    PIDFController controller;
    MotorEx shooter1;
    MotorEx shooter2;
    public static double p = 0.0000, d = 0, f = 0.00036;
    public static double setpoint;
    MultipleTelemetry tele;

    @Override
    public void init() {
        shooter1 = new MotorEx(hardwareMap, "chm0");
        shooter2 = new MotorEx(hardwareMap, "chm1");

        controller = new PIDFController(p,d,0,f);
        tele = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        shooter2.stopAndResetEncoder();


//        shooter1.setInverted(false);
//        shooter2.setInverted(true);
    }


    @Override
    public void loop() {
        controller.setP(p);
        controller.setD(d);
        controller.setF(f);

        controller.setSetPoint(setpoint);

        double velo = -shooter2.encoder.getCorrectedVelocity() / 28.0 * 60 * 0.75;
        double power = controller.calculate(velo);

        if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0) {
            shooter1.set(power);
            shooter2.set(power);
        } else {
            shooter1.set(gamepad1.right_trigger - gamepad1.left_trigger);
            shooter2.set(gamepad1.right_trigger - gamepad1.left_trigger);
        }

        tele.addData("velocity", velo);
        tele.addData("power", power);
//        tele.addData("right trigger", gamepad1.right_trigger);
//        tele.addData("left trigger", gamepad1.left_trigger);
        tele.update();

    }
}