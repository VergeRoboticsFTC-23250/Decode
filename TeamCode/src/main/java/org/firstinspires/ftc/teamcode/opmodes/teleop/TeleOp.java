package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.Snoopy;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TeleOp extends CommandOpMode {

    JoinedTelemetry tele;

    @Override
    public void initialize() {
        Snoopy.init(hardwareMap, Snoopy.MatchState.TELEOP, Snoopy.Alliance.BLUE);
        tele = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
    }

    public void run() {
        Snoopy.update();

        Snoopy.turret.enableAim = gamepad1.dpad_up;
        Snoopy.intake.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
        Snoopy.drivetrain.drive(gamepad1);

        if(gamepad1.cross){
            Snoopy.shoot();
        }else if(gamepad1.right_bumper){
            Snoopy.prime();
        }else{
            Snoopy.reset();
        }

        tele.addData("shooter speed", Snoopy.shooter.getVelocity());
        tele.addData("power", Snoopy.shooter.shooter1.get());

        tele.update();
    }
}