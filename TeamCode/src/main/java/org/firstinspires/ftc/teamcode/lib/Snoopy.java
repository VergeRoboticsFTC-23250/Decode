package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Robot;

import org.firstinspires.ftc.teamcode.lib.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.lib.subsystems.Intake;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

public class Snoopy extends Robot {
    public final Intake intake;
    public final Drivetrain drivetrain;
    public Snoopy(Globals g){
        intake = new Intake(g);
        drivetrain = new Drivetrain(g);
    }
    public Snoopy(HardwareMap hMap){
        this(new Globals(hMap));
    }
}
