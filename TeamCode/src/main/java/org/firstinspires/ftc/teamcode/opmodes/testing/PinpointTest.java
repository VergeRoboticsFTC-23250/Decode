package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp
public class PinpointTest extends OpMode {

    GoBildaPinpointDriver pinpoint;

    @Override
    public void init() {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setPosX(0, DistanceUnit.INCH);
        pinpoint.setPosY(0, DistanceUnit.INCH);
        pinpoint.setHeading(0, AngleUnit.DEGREES);
        pinpoint.resetPosAndIMU();
    }

    @Override
    public void loop() {
        pinpoint.update();

        telemetry.addData("x", pinpoint.getPosX(DistanceUnit.INCH));
        telemetry.addData("y", pinpoint.getPosY(DistanceUnit.INCH));
        telemetry.addData("heading", pinpoint.getHeading(AngleUnit.DEGREES));
    }
}
