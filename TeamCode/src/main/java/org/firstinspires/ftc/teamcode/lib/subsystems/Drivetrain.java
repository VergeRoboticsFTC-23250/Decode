package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.pedro.Constants;
import org.firstinspires.ftc.teamcode.lib.util.Globals;
import static org.firstinspires.ftc.teamcode.lib.util.Globals.MatchState.*;

@Config
public class Drivetrain extends SubsystemBase {
    private ServoEx pto;
    public static double engage = 0.3, disengage = 0.51;
    private Follower follower;
    public static boolean reverse = true;
    public Drivetrain(Globals g){
        pto = new ServoEx(g.hMap, "sh5");
        follower = Constants.createFollower(g.hMap);
        follower.setStartingPose(g.startPose);
        follower.update();

        if(g.matchState == TELE){
            follower.startTeleopDrive(true);
        }
        pto.set(disengage);
    }

    public void drive(Gamepad gamepad){
        if(reverse){
            follower.setTeleOpDrive(gamepad.right_stick_y, gamepad.right_stick_x, gamepad.left_stick_x, true);
        }else{
            follower.setTeleOpDrive(gamepad.left_stick_y, gamepad.left_stick_x, gamepad.right_stick_x, true);
        }
    }


    @Override
    public void periodic() {
        super.periodic();
        follower.update();
    }
}
