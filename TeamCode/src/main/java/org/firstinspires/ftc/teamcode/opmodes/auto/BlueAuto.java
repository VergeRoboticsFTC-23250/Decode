package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.utils.Paths;
import org.firstinspires.ftc.teamcode.utils.Snoopy;

@Autonomous
public class BlueAuto extends CommandOpMode {

    Paths paths;

    @Override
    public void initialize() {
        Snoopy.init(hardwareMap, Snoopy.MatchState.AUTO, Snoopy.Alliance.BLUE);
        paths = new Paths(Snoopy.drivetrain.follower);
        Snoopy.drivetrain.follower.setMaxPower(0.7);

        schedule(new SequentialCommandGroup(

            new FollowPathCommand(Snoopy.drivetrain.follower, paths.startToScore),

            new InstantCommand(Snoopy::prime),
            new WaitCommand(2000),
            new InstantCommand(Snoopy::shoot),
            new WaitCommand(1500),

            new InstantCommand(() -> {
                Snoopy.reset();
                Snoopy.drivetrain.follower.setMaxPower(0.5);
                Snoopy.intake.setPower(1);
                Snoopy.intake.setMinPower(1);
            }),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.intakeGPP1),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.intakeGPP2),
            new WaitCommand(500),

            new InstantCommand(() -> Snoopy.drivetrain.follower.setMaxPower(0.6)),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.scoreGPP),

            new InstantCommand(Snoopy::prime),
            new WaitCommand(2000),
            new InstantCommand(Snoopy::shoot),
            new WaitCommand(1500),

            new InstantCommand(() -> {
                Snoopy.reset();
                Snoopy.drivetrain.follower.setMaxPower(0.5);
                Snoopy.intake.setPower(1);
                Snoopy.intake.setMinPower(1);
            }),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.intakePGP1),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.intakePGP2),
            new WaitCommand(500),


            new InstantCommand(() -> Snoopy.drivetrain.follower.setMaxPower(0.6)),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.scorePGP),

            new InstantCommand(Snoopy::prime),
            new WaitCommand(2000),
            new InstantCommand(Snoopy::shoot),
            new WaitCommand(1500),


            new InstantCommand(() -> {
                Snoopy.reset();
                Snoopy.drivetrain.follower.setMaxPower(0.6);
                Snoopy.intake.setMinPower(0);
            }),
            new FollowPathCommand(Snoopy.drivetrain.follower, paths.park)
        ));
    }


    @Override
    public void run() {
        super.run();
        Snoopy.update();
    }
}
