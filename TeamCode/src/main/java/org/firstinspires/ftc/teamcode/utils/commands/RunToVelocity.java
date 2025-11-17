package org.firstinspires.ftc.teamcode.utils.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.utils.subsystems.Shooter;

public class RunToVelocity extends CommandBase {

    Shooter shooter;
    double velocity;
    public RunToVelocity(Shooter shoot, double velo) {
        shooter = shoot;
        velocity = velo;
        addRequirements(shoot);
    }

    public void init() {
        shooter.setTargetVelo(velocity);
    }
    public boolean isFinished() {
        return shooter.controller.atSetPoint();
    }
}
