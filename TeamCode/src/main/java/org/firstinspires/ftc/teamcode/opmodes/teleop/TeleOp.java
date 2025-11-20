package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.Snoopy;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TeleOp extends CommandOpMode {
    @Override
    public void initialize() {
        Snoopy.init(hardwareMap, Snoopy.MatchState.TELEOP, Snoopy.Alliance.BLUE);
    }

    public void run() {
        Snoopy.update(gamepad1);

        if(gamepad1.cross){
            Snoopy.turret.setAngle(Math.toRadians(0));
        }else if(gamepad1.circle){
            Snoopy.turret.setAngle(Math.toRadians(90));
        }else if(gamepad1.triangle){
            Snoopy.turret.setAngle(Math.toRadians(180));
        }else if(gamepad1.square){
            Snoopy.turret.setAngle(Math.toRadians(270));
        }

        telemetry.addData("turret angle", Math.toDegrees(Snoopy.turret.getAngle()));
        telemetry.addData("robot heading", Snoopy.getHeading());
        telemetry.update();
    }
}