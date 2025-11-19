package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.utils.Paths;
import org.firstinspires.ftc.teamcode.utils.Snoopy;
import org.firstinspires.ftc.teamcode.utils.commands.RunToVelocity;
import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Intake;
import org.firstinspires.ftc.teamcode.utils.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

@Autonomous
@Configurable
public class PedroTest extends CommandOpMode {

    Snoopy snoopy;
    Drivetrain drivetrain;
    Intake intake;
    Shooter shooter;
    Turret turret;

    Paths paths;

    @Override
    public void initialize() {
        snoopy = new Snoopy(hardwareMap, Snoopy.MatchState.AUTO, Snoopy.Alliance.BLUE);
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap);
        paths = new Paths(drivetrain.follower);

        register(drivetrain, intake, shooter, turret);

        drivetrain.follower.setStartingPose(new Pose(24.000, 126.500, Math.toRadians(90)));
        schedule(new SequentialCommandGroup(
                new ParallelCommandGroup(
                        new InstantCommand(() -> {
                            turret.useFacingPoint = false;
                            shooter.setStopper(Shooter.stopperClosed);
                            intake.setPower(0.2);
                        }),
                        new RunToVelocity(shooter, Shooter.maxVelo * 0.5),
                        new FollowPathCommand(drivetrain.follower, paths.startToScore)
                ),
                new WaitCommand(1000),
                new InstantCommand(() -> {
                    intake.setPower(1);
                    turret.useFacingPoint = true;
                    turret.setTarget(-45);
                    shooter.setStopper(Shooter.stopperOpen);
                }),
                new WaitCommand(1000),
                new ParallelCommandGroup(
                        new InstantCommand(() -> {
                            intake.setPower(1);
                            turret.useFacingPoint = false;
                        }),
                        new FollowPathCommand(drivetrain.follower, paths.intakeGPP)
                )
        ));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        drivetrain.follower.update();
        if (turret.useFacingPoint) {
            turret.update();
        } else {
            turret.update(0);
        }
        shooter.update();

        telemetry.addData("x", drivetrain.follower.getPose().getX());
        telemetry.addData("y", drivetrain.follower.getPose().getY());
        telemetry.addData("heading", drivetrain.follower.getPose().getHeading());
        telemetry.addData("error", shooter.controller.getVelocityError());
        telemetry.update();
    }
}