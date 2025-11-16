package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TurretLock extends CommandOpMode {

    Turret turret;
    Drivetrain drivetrain;

    @Override
    public void initialize() {
        turret = new Turret(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        register(turret, drivetrain);
    }

    public void run() {

        // update subsystems
        drivetrain.fieldCentric(gamepad1);
        turret.update(-drivetrain.getHeading());

        // telemetry
        telemetry.addData("heading", drivetrain.getHeading());
        telemetry.addData("setpoint", turret.getTarget());
        telemetry.addData("position", turret.turret.getCurrentPosition() * turret.degreesPerTick);
        telemetry.update();
    }
}
