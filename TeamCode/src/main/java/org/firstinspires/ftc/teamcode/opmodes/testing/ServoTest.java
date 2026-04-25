package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

//@Config
//@TeleOp
public class ServoTest extends OpMode {
    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

    //intake : .92 up, .795 down port sh3
    //hood port sh4
    //pto : .51 disengaged, .3 engaged port sh5
    //stopper : open .535, closed .38 port ehs0


    public ServoEx servo;
    public static String name = "sh3";
    public static double pos;
    public static boolean reversed = false;

    @Override
    public void init() {
        servo = new ServoEx(hardwareMap, name);
        servo.setInverted(reversed);
    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        servo.set(pos);
    }
}
