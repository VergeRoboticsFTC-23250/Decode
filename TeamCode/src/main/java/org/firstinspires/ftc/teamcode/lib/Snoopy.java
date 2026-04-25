package org.firstinspires.ftc.teamcode.lib;

import com.seattlesolvers.solverslib.command.Robot;

import org.firstinspires.ftc.teamcode.lib.subsystems.Intake;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

public class Snoopy extends Robot {
    public final Intake intake;
    public Snoopy(Globals g){
        intake = new Intake(g);
    }
}
