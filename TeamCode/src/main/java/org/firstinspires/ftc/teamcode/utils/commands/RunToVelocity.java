package org.firstinspires.ftc.teamcode.utils.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.utils.subsystems.Shooter;

public class RunToVelocity extends CommandBase {

    Shooter shooter;
    double velocity;
//    Telemetry telemetry;
    public RunToVelocity(Shooter shoot, double velo) {
        shooter = shoot;
        velocity = velo;
//        telemetry = tele;
        addRequirements(shoot);
    }

    public void init() {
        shooter.setTargetVelo(velocity);
    }

    public void execute() {
        shooter.setTargetVelo(velocity);
        shooter.update();
    }
    public boolean isFinished() {
        return (-shooter.shooter1.getCorrectedVelocity() - velocity) < Shooter.tolerance;
    }
}