package org.firstinspires.ftc.teamcode.lib;

import com.pedropathing.math.Vector;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.Robot;

import org.firstinspires.ftc.teamcode.lib.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.lib.subsystems.Intake;
import org.firstinspires.ftc.teamcode.lib.subsystems.Turret;
import org.firstinspires.ftc.teamcode.lib.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

public class Snoopy extends Robot {
    Globals g;
    public final Intake intake;
    public final Drivetrain drivetrain;
    public final Shooter shooter;
    public final Turret turret;
    public Snoopy(Globals g){
        this.g = g;
        intake = new Intake(g);
        drivetrain = new Drivetrain(g);
        shooter = new Shooter(g);
        turret = new Turret(g);
    }
    public Snoopy(CommandOpMode opMode){
        this(new Globals(opMode));
    }

    @Override
    public void run() {
        super.run();
        Vector dist = drivetrain.getDistanceFromGoal();
        shooter.update(dist.getMagnitude());
        double angle = dist.getTheta();
        g.telemetry.addData("error angle", Math.toDegrees(angle));
        turret.setAngle(angle);
        g.telemetry.update();
    }
}
