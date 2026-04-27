package org.firstinspires.ftc.teamcode.lib;

import android.provider.Settings;

import com.pedropathing.math.Vector;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.command.RunCommand;

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
        double mag = dist.getMagnitude();
        shooter.update(mag);
        turret.setAngle(dist.getTheta() - drivetrain.follower.getHeading() + (mag > 120 ? Math.toRadians(g.turretOffsetFar) : 0));
        g.telemetry.addData("alliance", Globals.ALLIANCE == Globals.Alliance.RED ? "RED" : "BLUE");
        g.telemetry.update();
    }

    public Command shoot() {
        return new RunCommand(() -> {
            intake.run(1, true);
            shooter.stopper.open();
        }, intake, shooter.stopper);
    }
}
