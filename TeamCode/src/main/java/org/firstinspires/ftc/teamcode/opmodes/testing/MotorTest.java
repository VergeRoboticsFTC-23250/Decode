package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

//@TeleOp
//@Config
public class MotorTest extends OpMode {

    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

    // ehm0 shooter1, reversed
    // ehm1 shooter2, no reverse
    // ehm2 transfer, no reverse
    // ehm3 intake, no reverse

    public MotorEx motor1, motor2;
    public static boolean motor2Enabled = true, reverseMotor1, reverseMotor2;
    public static String name1 = "ehm2", name2 = "ehm3";
    public static double power;

    @Override
    public void init() {
        motor1 = new MotorEx(hardwareMap, name1);
        motor1.setInverted(reverseMotor1);
        if (motor2Enabled) {
            motor2 = new MotorEx(hardwareMap, name2);
            motor2.setInverted(reverseMotor2);
        }

    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        motor1.set(power);
        if (motor2Enabled) motor2.set(power);
    }
}
