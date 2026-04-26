package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.lib.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Autonomous(name = "RedAuto")
public class RedAuto extends CommandOpMode {

    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */

    Drivetrain drivetrain;
    Globals globals;

    @Override
    public void initialize() {
        globals = new Globals(this, Globals.Alliance.RED);
        drivetrain = new Drivetrain(globals);
        drivetrain.buildPaths();
        schedule(
                new SequentialCommandGroup(
                        new FollowPathCommand(
                                drivetrain.follower,
                                drivetrain.preload
                        ),
                        new WaitCommand(500),
                        new FollowPathCommand(
                                drivetrain.follower,
                                drivetrain.intakeFirst
                        ),
                        new WaitCommand(500),
                        new FollowPathCommand(
                                drivetrain.follower,
                                drivetrain.shootFirst
                        ),
                        new WaitCommand(500),
                        new FollowPathCommand(
                                drivetrain.follower,
                                drivetrain.intakeSecond
                        ),
                        new WaitCommand(500),
                        new FollowPathCommand(
                                drivetrain.follower,
                                drivetrain.shootSecond
                        )
                )
        );
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


    }
}
