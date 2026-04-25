package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

@Config
@TeleOp
public class DrivetrainTest extends OpMode {
    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

    MotorEx frontRight, frontLeft, backLeft, backRight;
    ServoEx pto;
//    public static boolean frontRightReverse, frontLeftReversed, backLeftReversed, backRightReversed;
//    public static double frontRightPow, frontLeftPow, backLeftPow, backRightPow;
    public static double ptoPos = 0.51;
    public static boolean useJoysticks = false;
    public static double backPower = 0;
//    public static String frontRightPort = "", frontLeftPort = "", backRightPort = "", backLeftPort = "";

    @Override
    public void init() {
        frontRight = new MotorEx(hardwareMap, "chm2");
        frontLeft = new MotorEx(hardwareMap, "chm1");
        backLeft = new MotorEx(hardwareMap, "chm0");
        backRight = new MotorEx(hardwareMap, "chm3");
        pto = new ServoEx(hardwareMap, "sh5");

        frontRight.setInverted(true);
        frontLeft.setInverted(false);
        backLeft.setInverted(false);
        backRight.setInverted(true);
    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        pto.set(ptoPos);

        if (useJoysticks) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeft.set(frontLeftPower);
            backLeft.set(backLeftPower);
            frontRight.set(frontRightPower);
            backRight.set(backRightPower);
        } else {
            backRight.set(backPower);
            backLeft.set(backPower);
        }


    }
}
