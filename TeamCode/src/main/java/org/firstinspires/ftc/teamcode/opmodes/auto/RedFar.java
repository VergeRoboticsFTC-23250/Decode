package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.lib.Snoopy;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Autonomous(name = "RedAuto")
public class RedFar extends CommandOpMode {

    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

    Snoopy snoop;
    Globals g;

    @Override
    public void initialize() {
        g = new Globals(this, Globals.Alliance.RED);
        g.startPose = new Pose(89,7, Math.toRadians(0));
        snoop = new Snoopy(g);
        schedule( new SequentialCommandGroup(
                pathTo(snoop.drivetrain.preload),
                shootFor(1000),

                new InstantCommand(() -> snoop.intake.run(1, true)),
                pathTo(snoop.drivetrain.intakeFirst),
                new InstantCommand(() -> snoop.intake.run(0, true)),

                pathTo(snoop.drivetrain.shootFirst),
                shootFor(1000),

                new InstantCommand(() -> snoop.intake.run(1, true)),
                pathTo(snoop.drivetrain.intakeSecond1),
                new InstantCommand(() -> snoop.intake.run(1, true)),
                pathTo(snoop.drivetrain.shootSecond),
                shootFor(1000)
        ));
    }

    /**
     * User-defined loop method
     * <p>
     * This method will be called repeatedly during the period between when
     * the play button is pressed and when the OpMode is stopped.
     */
    @Override
    public void run() {
        super.run();
        snoop.run();
    }

    public FollowPathCommand pathTo(PathChain path) {
        return new FollowPathCommand(
                snoop.drivetrain.follower,
                path,
                true
        );
    }

    public Command shootFor(long ms) {
        return new ParallelDeadlineGroup(
                new WaitCommand(ms),
                snoop.shoot()
        );
    }
}
