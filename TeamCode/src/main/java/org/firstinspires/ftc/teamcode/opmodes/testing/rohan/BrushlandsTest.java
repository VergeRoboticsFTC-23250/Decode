package org.firstinspires.ftc.teamcode.opmodes.testing.rohan;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

//@TeleOp
public class BrushlandsTest extends OpMode {
    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */
    DigitalChannel back0, back1, mid0, mid1, forward0, forward1;

//    AnalogInput pin0;
//    DigitalChannel pin0, pin1;
    @Override
    public void init() {
        back0 = hardwareMap.digitalChannel.get("dpin0");
        back1 = hardwareMap.digitalChannel.get("dpin1");
        mid0 = hardwareMap.digitalChannel.get("dpin2");
        mid1 = hardwareMap.digitalChannel.get("dpin3");
        forward0 = hardwareMap.digitalChannel.get("dpin4");
        forward1 = hardwareMap.digitalChannel.get("dpin5");

    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void loop() {
        telemetry.addData("back color", back0.getState());
        telemetry.addData("back proximity", back1.getState());

        telemetry.addData("mid color", mid0.getState());
        telemetry.addData("mid proximity", mid1.getState());

        telemetry.addData("forward color", forward0.getState());
        telemetry.addData("forward proximity", forward1.getState());

        telemetry.update();
    }
}
