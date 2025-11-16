package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.Snoopy;
import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

public class BlueAuto extends CommandOpMode {

    Snoopy snoopy;
    Drivetrain drivetrain;
    Turret turret;
    @Override
    public void initialize() {
        snoopy = new Snoopy(hardwareMap, Snoopy.MatchState.AUTO, Snoopy.Alliance.BLUE);
        drivetrain = new Drivetrain(hardwareMap);
        turret = new Turret(hardwareMap);

        register(turret, drivetrain);
    }
}
