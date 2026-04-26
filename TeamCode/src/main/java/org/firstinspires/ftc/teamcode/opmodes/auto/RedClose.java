package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.lib.Snoopy;
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
               pathTo(snoop.drivetrain.preload),
               new InstantCommand(() -> snoop.intake.run(1, true)),
               shootFor(1000),


               new InstantCommand(() -> snoop.intake.run(1, true)),
               pathTo(snoop.drivetrain.intakeFirst, 0.6),
               new WaitCommand(1000),
//               new InstantCommand(() -> snoop.intake.run(0, true)),

               pathTo(snoop.drivetrain.shootFirst),
               shootFor(1000),

               new InstantCommand(() -> snoop.intake.run(1, true)),
               pathTo(snoop.drivetrain.intakeSecond1, 0.6),
               new WaitCommand(1000),
//               new InstantCommand(() -> snoop.intake.run(0, true)),
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
