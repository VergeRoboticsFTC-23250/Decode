package org.firstinspires.ftc.teamcode.lib.pedro;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(12.5)
            .headingPIDFCoefficients(new PIDFCoefficients(2, 0, 0.1, 0.01))
            .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0.1, 0.06707059, 0.00204113))
            .centripetalScaling(0)
            .automaticHoldEnd(true);

    public static MecanumConstants mecanumConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("chm2")
            .rightRearMotorName("chm3")
            .leftRearMotorName("chm0")
            .leftFrontMotorName("chm1")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(78.39194)
            .yVelocity(60.78802);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-2.353)
            .strafePodX(-2.765)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static PathConstraints pathConstraints = new PathConstraints(0.95, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(mecanumConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
}

// 12.9V battery
// quadratic 0.001967478
// 0.0019177547
// 0.0020733528
// 0.002051337
// 0.0021530439
// 0.00217832633
// 0.0019466116293

// linear 0.0767339
// 0.0569257
// 0.06429417
// 0.069067828
// 0.0679363
// 0.05858458227
// 0.0759516594505
