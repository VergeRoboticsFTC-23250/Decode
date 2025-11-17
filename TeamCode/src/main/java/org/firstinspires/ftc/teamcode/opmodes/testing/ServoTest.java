package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Configurable
@TeleOp
public class ServoTest extends OpMode {
    Servo servo;
    public static double pos = 0.5;
    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "stopper");
    }

    @Override
    public void loop() {
        servo.setPosition(pos);
    }
}
