package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.utils.Snoopy;

public class BlueAuto extends CommandOpMode {

    @Override
    public void initialize() {
        Snoopy.init(hardwareMap, Snoopy.MatchState.AUTO, Snoopy.Alliance.BLUE);

        schedule(new SequentialCommandGroup(

        ));
    }

    @Override
    public void run() {
        super.run();
        Snoopy.update();
    }
}
