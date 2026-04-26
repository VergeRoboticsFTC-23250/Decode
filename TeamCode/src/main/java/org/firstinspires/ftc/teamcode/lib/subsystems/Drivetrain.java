package org.firstinspires.ftc.teamcode.lib.subsystems;

import static org.firstinspires.ftc.teamcode.lib.util.Globals.MatchState.TELE;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.pedro.Constants;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Drivetrain extends SubsystemBase {
    private ServoEx pto;
    public static double engage = 0.3, disengage = 0.51;
    public Follower follower;
    public static boolean reverse = true;

    public PathChain preload, intakeFirst, shootFirst, intakeSecond, shootSecond;

    public Drivetrain(Globals g){
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

    public void buildPaths() {
        preload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(117.000, 127.000),
                                new Pose(96.000, 96.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(315))
                .build();

        intakeFirst = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(96.000, 96.000),
                                new Pose(102.727, 81.375),
                                new Pose(128.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootFirst = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(128.000, 84.000),
                                new Pose(86.000, 86.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intakeSecond = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(86.000, 86.000),
                                new Pose(88.571, 56.845),
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
}
