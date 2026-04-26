package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.lib.Snoopy;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

@TeleOp(name = "TeleOp")
public class Tele extends CommandOpMode {
    Globals g;
    Snoopy snoopy;
    GamepadEx arv;
    @Override
    public void initialize() {
        g = new Globals(this);
        snoopy = new Snoopy(this);

        arv = new GamepadEx(gamepad1);

        Command shoot = snoopy.shoot();

        arv.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(shoot).whenReleased(new InstantCommand(shoot::cancel));
    }

    @Override
    public void run() {
        super.run();
        snoopy.run();
        snoopy.intake.setDefaultCommand(new RunCommand(() -> snoopy.intake.run(gamepad1.right_trigger - gamepad1.left_trigger), snoopy.intake));

        snoopy.drivetrain.drive(gamepad1);
    }
}
