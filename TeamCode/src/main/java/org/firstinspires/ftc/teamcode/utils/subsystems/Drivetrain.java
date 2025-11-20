package org.firstinspires.ftc.teamcode.utils.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.control.PIDFController;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Configurable
public class Drivetrain extends SubsystemBase {
    public Follower follower;
    public static double slow = .2;
    public PIDFController headingController;

    public Drivetrain(HardwareMap hardwareMap) {
        follower = Constants.createFollower(hardwareMap);
        follower.startTeleopDrive(true);
        headingController = new PIDFController(follower.constants.coefficientsHeadingPIDF);
        follower.update();
    }

    public void fieldCentric(Gamepad gamepad1) {
        follower.setTeleOpDrive(
                -gamepad1.right_stick_y,
                -gamepad1.right_stick_x,
                -gamepad1.left_stick_x,
                false
        );
        follower.update();
    }

    public void robotCentric(Gamepad gamepad1) {

        double multiplier = gamepad1.left_bumper? slow : 1;

        follower.setTeleOpDrive(
                -gamepad1.right_stick_y * multiplier,
                -gamepad1.right_stick_x * multiplier,
                -gamepad1.left_stick_x * .5 * multiplier,
                true
        );
        follower.update();
    }
}