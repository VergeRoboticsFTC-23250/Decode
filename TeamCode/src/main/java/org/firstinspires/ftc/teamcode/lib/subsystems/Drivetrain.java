package org.firstinspires.ftc.teamcode.lib.subsystems;

import static org.firstinspires.ftc.teamcode.lib.util.Globals.MatchState.TELE;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.pedro.Constants;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Drivetrain extends SubsystemBase {
    private ServoEx pto;
    public static double engage = 0.2, disengage = 0.375;
    public Follower follower;
    public static boolean reverse = true;

    public PathChain preload, intakeFirst, shootFirst, intakeSecond1, intakeSecond2, intakeSecond3, shootSecond, intakeThird1, intakeThird2, shootThird, intakeGate1, intakeGate2, intakeGate3, shootGate;
    Globals g;

    public Drivetrain(Globals g){
        this.g = g;
        pto = new ServoEx(g.hMap, "sh5");
        follower = Constants.createFollower(g.hMap);
        follower.setStartingPose(Globals.getInstance().startPose);
        follower.update();

        if(g.matchState == TELE){
            follower.startTeleopDrive(true);
        }
        pto.set(disengage);
    }

    public void drive(Gamepad gamepad){
        if(reverse){
            follower.setTeleOpDrive(-gamepad.right_stick_y, -gamepad.right_stick_x, -gamepad.left_stick_x, true);
        }else{
            follower.setTeleOpDrive(-gamepad.left_stick_y, -gamepad.left_stick_x, -gamepad.right_stick_x, true);
        }
    }


    @Override
    public void periodic() {
        super.periodic();
        follower.update();
    }

    public Vector getDistanceFromGoal(){
        Pose pose = follower.getPose();
        g.telemetry.addData("x", pose.getX());
        g.telemetry.addData("y", pose.getY());
        g.telemetry.addData("h", Math.toDegrees(pose.getHeading()));
        return new Vector(g.goalPose.minus(pose));
    }

    public void buildPathsCloseRed() {
        preload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(117.000, 127.000),
                                new Pose(94.000, 84.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(94.000, 84.000),
                                new Pose(126.000, 84.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(126.000, 84.000),
                                new Pose(84.000, 76.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intakeSecond1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 76.000),
                                new Pose(96.000, 60.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeSecond2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(96.000, 60.000),
                                new Pose(136.000, 60.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootSecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(136.000, 60.000),
                                new Pose(84.000, 76.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();
        intakeThird1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 76.000),
                                new Pose(96.000, 36.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeThird2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(96.000, 36.000),
                                new Pose(136.000, 36.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootThird = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(136.000, 36.000),
                                new Pose(84.000, 76.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intakeGate1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 76.000),
                                new Pose(132.000, 62.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(30))
                .build();

        intakeGate2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(132.000, 62.000),
                                new Pose(133.000, 52.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(70))
                .build();
    }

    public void buildPathsFarRed() {
        intakeFirst = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(89.000, 7.000),
                                new Pose(84.900, 39.416),
                                new Pose(134.000, 35.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(134.000, 35.000),
                                new Pose(93.000, 9.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeSecond1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(93.000, 9.000),
                                new Pose(135.000, 15.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeSecond2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(135.000, 15.000),
                                new Pose(128.000, 7.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        intakeSecond3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(128.000, 7.000),
                                new Pose(133.000, 7.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootSecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(133.000, 7.000),
                                new Pose(98.000, 7.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();


    }

    public void buildPathsCloseBlue() {
        preload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(117.000, 127.000).mirror(),
                                new Pose(94.000, 84.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakeFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(94.000, 84.000).mirror(),
                                new Pose(126.000, 84.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        shootFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(126.000, 84.000).mirror(),
                                new Pose(84.000, 76.000).mirror()
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intakeSecond1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 76.000).mirror(),
                                new Pose(96.000, 60.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakeSecond2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(96.000, 60.000).mirror(),
                                new Pose(136.000, 60.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        shootSecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(136.000, 60.000).mirror(),
                                new Pose(84.000, 76.000).mirror()
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();
        intakeThird1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 76.000).mirror(),
                                new Pose(96.000, 36.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakeThird2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(96.000, 36.000).mirror(),
                                new Pose(136.000, 36.000).mirror()
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        shootThird = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(136.000, 36.000).mirror(),
                                new Pose(84.000, 76.000).mirror()
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

//        intakeGate1 = follower.pathBuilder()
//                .addPath(
//                        new BezierLine(
//                                new Pose(84.000, 76.000).mirror(),
//                                new Pose(132.000, 62.000).mirror()
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(30))
//                .build();
//
//        intakeGate2 = follower.pathBuilder()
//                .addPath(
//                        new BezierLine(
//                                new Pose(132.000, 62.000).mirror(),
//                                new Pose(133.000, 52.000).mirror()
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(70))
//                .build();
    }
}
