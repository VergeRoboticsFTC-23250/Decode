package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorGoBildaPinpoint;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TeleOp extends CommandOpMode {

    Turret turret;
    Drivetrain drivetrain;
    GoBildaPinpointDriver pinpoint;

    @Override
    public void initialize() {
        turret = new Turret(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        register(turret, drivetrain);

//        turret.setTarget(90);
    }

    public void run() {
        drivetrain.fieldCentric(gamepad1);
        turret.update(-drivetrain.getHeading());
        telemetry.addData("heading", drivetrain.getHeading());
        telemetry.addData("setpoint", turret.getTarget());
        telemetry.addData("position", turret.turret.getCurrentPosition() * turret.degreesPerTick);
        telemetry.update();
    }
}