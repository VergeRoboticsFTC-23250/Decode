package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {

    public PathChain startToScore;
    public PathChain intakeGPP;
    public PathChain scoreGPP;
    public PathChain intakePGP1;
    public PathChain intakePGP2;
    public PathChain scorePGP;
    public PathChain park;

    public Paths(Follower follower) {
        startToScore = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(24.000, 126.500), new Pose(60.000, 83.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();

        intakeGPP = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.000, 83.500), new Pose(17.000, 84.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        scoreGPP = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(17.000, 84.000), new Pose(60.000, 83.500))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakePGP1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.000, 83.500), new Pose(40.000, 62.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        intakePGP2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(40.000, 62.000), new Pose(8.500, 62.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        scorePGP = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(8.500, 62.000), new Pose(60.000, 83.500))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        park = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.000, 83.500), new Pose(22.000, 72.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                .build();
    }
}