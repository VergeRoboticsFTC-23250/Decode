package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {

    public PathChain startToScore;
    public PathChain intakeGPP1;
    public PathChain intakeGPP2;
    public PathChain scoreGPP;
    public PathChain intakePGP1;
    public PathChain intakePGP2;
    public PathChain scorePGP;
    public PathChain park;

    public Pose shootingPose = new Pose(32,112, Math.toRadians(90));

    public Paths(Follower follower) {
        startToScore = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(24.000, 126.500), new Pose(32.000, 112.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();

        intakeGPP1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(32.000, 112.000), new Pose(45.000, 84.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakeGPP2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(45.000, 84.000), new Pose(17.000, 84.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        scoreGPP = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(17.000, 84.000), new Pose(32.000, 112.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakePGP1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(32.000, 112.000), new Pose(45.000, 60.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakePGP2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(45.000, 60.000), new Pose(8.500, 60.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        scorePGP = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(8.500, 60.000),
                                new Pose(49.627, 54.717),
                                new Pose(32.000, 112.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        park = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(32.000, 112.000), new Pose(22.000, 72.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                .build();
    }
}