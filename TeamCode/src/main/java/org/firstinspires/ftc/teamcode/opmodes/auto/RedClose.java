package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.lib.Snoopy;
import org.firstinspires.ftc.teamcode.lib.subsystems.Turret;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Autonomous(name = "RedClose")
public class RedClose extends CommandOpMode {

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
        snoop = new Snoopy(g);
        //Command shoot = snoop.shoot();
        snoop.drivetrain.buildPathsCloseRed();
        schedule( new SequentialCommandGroup(
                new InstantCommand(() -> Turret.offset = 0),
                pathTo(snoop.drivetrain.preload),
                new InstantCommand(() -> snoop.intake.run(1)),
                shootFor(1000),

                new InstantCommand(() -> Turret.offset = -4),
                new InstantCommand(() -> snoop.intake.run(1)),
                pathTo(snoop.drivetrain.intakeFirst, 0.5),
                new WaitUntilCommand(() -> snoop.intake.isFull()).withTimeout(1000),


                pathTo(snoop.drivetrain.shootFirst),
                new WaitCommand(750),
                shootFor(1000),

                new InstantCommand(() -> snoop.intake.run(1)),
                pathTo(snoop.drivetrain.intakeSecond1),
                pathTo(snoop.drivetrain.intakeSecond2, 0.5),
                new WaitUntilCommand(() -> snoop.intake.isFull()).withTimeout(1000),
                pathTo(snoop.drivetrain.shootSecond),
                new WaitCommand(750),
                shootFor(1000),

                new InstantCommand(() -> snoop.intake.run(1)),
                pathTo(snoop.drivetrain.intakeThird1),
                pathTo(snoop.drivetrain.intakeThird2, 0.5),
                new WaitUntilCommand(() -> snoop.intake.isFull()).withTimeout(1000),
                pathTo(snoop.drivetrain.shootThird),
                new WaitCommand(750),
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

    public FollowPathCommand pathTo(PathChain path, double maxPow) {
        return new FollowPathCommand(
                snoop.drivetrain.follower,
                path,
                true,
                maxPow
        );
    }

    public Command shootFor(long ms) {
        Command shoot = snoop.shoot();
        return new SequentialCommandGroup(
                shoot.withTimeout(ms),
                new InstantCommand(shoot::cancel),
                new InstantCommand(() -> snoop.intake.run(0)),
                new InstantCommand(() -> snoop.shooter.stopper.close())
        );
    }
}
