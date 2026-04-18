package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class BrushlandsTest extends OpMode {
    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

//    AnalogInput pin0;
    DigitalChannel pin0, pin1;
    @Override
    public void init() {
        pin0 = hardwareMap.digitalChannel.get("pin0");
        pin1 = hardwareMap.digitalChannel.get("pin1");
    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        telemetry.addData("purple or green", pin0.getState());
        telemetry.addData("distance threshold", pin1.getState());
        telemetry.update();
    }
}
