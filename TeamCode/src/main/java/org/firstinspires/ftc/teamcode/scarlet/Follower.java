package org.firstinspires.ftc.teamcode.scarlet;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Follower {
    public GoBildaPinpointDriver pinpoint;

    public MotorEx frontLeft;
    public MotorEx frontRight;
    public MotorEx backLeft;
    public MotorEx backRight;

    public Follower(GoBildaPinpointDriver pp, HardwareMap hMap) {
        pinpoint = pp;
        pinpoint.setEncoderResolution(Constants.encoderRes);
        pinpoint.setOffsets(Constants.xOffset, Constants.yOffset, DistanceUnit.INCH);
        pinpoint.setEncoderDirections(Constants.forwardPodDirection, Constants.strafePodDirection);

        frontLeft = new MotorEx(hMap, Constants.frontLeft);
        frontRight = new MotorEx(hMap, Constants.frontRight);
        backLeft = new MotorEx(hMap, Constants.backLeft);
        backRight = new MotorEx(hMap, Constants.backRight);

    }
}