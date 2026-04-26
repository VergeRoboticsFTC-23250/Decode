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

    public PathChain preload, intakeFirst, shootFirst, intakeSecond1, intakeSecond2, intakeSecond3, shootSecond;
    Globals g;

    public Drivetrain(Globals g){
        this.g = g;
        pto = new ServoEx(g.hMap, "sh5");
        follower = Constants.createFollower(g.hMap);
        follower.setStartingPose(g.startPose);
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
            follower.setTeleOpDrive(gamepad.left_stick_y, gamepad.left_stick_x, gamepad.right_stick_x, true);
        }
    }


    @Override
    public void periodic() {
        super.periodic();
        follower.update();
    }

    public Vector getDistanceFromGoal(){
        return new Vector(g.goalPose.minus(follower.getPose()));
    }

    public void buildPathsCloseRed() {
        preload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(117.000, 127.000),
                                new Pose(90.000, 96.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(315))
                .build();

        intakeFirst = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(90.000, 96.000),
                                new Pose(95.727, 81.375),
                                new Pose(128.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(128.000, 84.000),
                                new Pose(83.000, 86.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intakeSecond1 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(83.000, 86.000),
                                new Pose(82.571, 56.845),
                                new Pose(136.000, 60.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootSecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(136.000, 60.000),
                                new Pose(78.341, 77.907)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
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
                                new Pose(135.000, 7.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        shootSecond = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(135.000, 7.000),
                                new Pose(98.000, 7.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }

//    public void buildPathsCloseBlue() {
//        buildPathsCloseRed();
//
//         mirror all of them
//    }
}
