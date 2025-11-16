package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Intake;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Configurable
public class TeleOp extends CommandOpMode {

    Turret turret;
    Drivetrain drivetrain;
    Intake intake;

    // loop timing
    private long lastLoopTimeNs = 0;
    private double loopMsAvg = 0.0;
    private static final double LOOP_ALPHA = 0.1; // EMA factor

    @Override
    public void initialize() {
        turret = new Turret(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        register(turret, drivetrain, intake);
    }

    public void run() {

        // update subsystems
        drivetrain.fieldCentric(gamepad1);
        turret.updateFacingPoint(0.01,0.01, drivetrain.follower.getPose());
        intake.setPower(gamepad1.right_trigger - gamepad1.left_trigger);

        // loop timing
        long now = System.nanoTime();
        double loopMs = 0.0;
        if (lastLoopTimeNs != 0) {
            loopMs = (now - lastLoopTimeNs) / 1e6;
            if (loopMsAvg == 0.0) loopMsAvg = loopMs;
            else loopMsAvg = loopMsAvg * (1.0 - LOOP_ALPHA) + loopMs * LOOP_ALPHA;
        }
        lastLoopTimeNs = now;

        // telemetry
        telemetry.addData("heading", drivetrain.getHeading());
        telemetry.addData("setpoint", turret.getTarget());
        telemetry.addData("position", turret.turret.getCurrentPosition() * turret.degreesPerTick);
        telemetry.addData("loopMs", String.format("%.2f", loopMs));
        telemetry.addData("loopMsAvg", String.format("%.2f", loopMsAvg));
        telemetry.addData("loopHz", loopMs > 0.0 ? String.format("%.1f", 1000.0 / loopMs) : "N/A");
        telemetry.update();
    }


}
