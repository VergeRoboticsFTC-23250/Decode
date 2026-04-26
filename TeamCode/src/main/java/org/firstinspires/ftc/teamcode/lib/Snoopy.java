package org.firstinspires.ftc.teamcode.lib;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.Robot;

import org.firstinspires.ftc.teamcode.lib.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.lib.subsystems.Intake;
import org.firstinspires.ftc.teamcode.lib.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

public class Snoopy extends Robot {
    Globals g;
    public final Intake intake;
    public final Drivetrain drivetrain;
    public final Shooter shooter;
    public Snoopy(Globals g){
        this.g = g;
        intake = new Intake(g);
        drivetrain = new Drivetrain(g);
        shooter = new Shooter(g);
    }
    public Snoopy(CommandOpMode opMode){
        this(new Globals(opMode));
    }

    @Override
    public void run() {
        super.run();
        shooter.update(drivetrain.getDistanceFromGoal().getMagnitude());
        g.telemetry.update();
    }
}
