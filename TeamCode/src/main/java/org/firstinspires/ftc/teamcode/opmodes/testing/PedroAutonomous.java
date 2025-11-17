package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable // Panels
public class PedroAutonomous extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(24, 126.5, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths
        pathState = 1;

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing

        switch (pathState) {
            case 1:
                follower.followPath(paths.startToScore);
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(paths.intakeGPP);
                    pathState = 3;
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(paths.scoreGPP);
                    pathState = 4;
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(paths.intakePGP1);
                    pathState = 5;
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(paths.intakePGP2);
                    pathState = 6;
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(paths.scorePGP);
                    pathState = 7;
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    follower.followPath(paths.park);
                    pathState = 8;
                }
                break;
            case 8:
                if (!follower.isBusy()) stop();
                break;
        }

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

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
                            new BezierLine(new Pose(60.000, 83.500), new Pose(12.000, 84.000))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();

            scoreGPP = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(12.000, 84.000), new Pose(60.000, 83.500))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();

            intakePGP1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(60.000, 83.500), new Pose(40.000, 59.500))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();

            intakePGP2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(40.000, 59.500), new Pose(7.000, 59.500))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();

            scorePGP = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(7.000, 59.500), new Pose(60.000, 83.500))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();

            park = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(60.000, 83.500), new Pose(20.000, 72.000))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build();
        }
    }
}
