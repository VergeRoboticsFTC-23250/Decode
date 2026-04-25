package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.LinearMapper;

@Config
@TeleOp
public class PIDFTest extends OpMode {

    PIDFController controller;
    MotorEx shooter1;
    MotorEx shooter2;
    ServoEx hood;
    LinearMapper mapper;
    public static double p = 0.0036, d = 0, f = 0.00029;
    public static double hoodMin = 0.395, hoodMax = 0.87;
    public static double hoodPos = 0;
    public static double setpoint = 1000;
    MultipleTelemetry tele;

    @Override
    public void init() {
        shooter1 = new MotorEx(hardwareMap, "chm0");
        shooter2 = new MotorEx(hardwareMap, "chm1");
        hood = new ServoEx(hardwareMap, "chs0");
        hood.setInverted(true);

        mapper = new LinearMapper(0, 1, hoodMin, hoodMax);
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
        hood.set(mapper.map(hoodPos));

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