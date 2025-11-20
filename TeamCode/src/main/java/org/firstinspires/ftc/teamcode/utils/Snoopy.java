package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.utils.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.subsystems.Intake;
import org.firstinspires.ftc.teamcode.utils.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.utils.subsystems.Turret;

public class Snoopy {
    public static enum MatchState {
        AUTO,
        TELEOP
    }
    public static enum Alliance {
        RED,
        BLUE
    }

    public static MatchState matchState;
    public static Alliance alliance;
    public static Drivetrain drivetrain;
    public static Turret turret;
    public static Intake intake;
    public static Shooter shooter;
    public static Pose startPose;

    private static Pose blueStartPose = new Pose(24, 126.5, Math.toRadians(90));
    private static Pose redStartPose = new Pose(144-blueStartPose.getX(), blueStartPose.getY(), Math.toRadians(90));

    public static Vector2d goal;
    private static Vector2d blueGoal = new Vector2d(0, 144);
    private static Vector2d redGoal = new Vector2d(144, 144);


    public static void init(HardwareMap hardwareMap, MatchState matchState, Alliance alliance) {
        Snoopy.matchState = matchState;
        Snoopy.alliance = alliance;
        Snoopy.startPose = alliance == Alliance.RED? redStartPose : blueStartPose;
        Snoopy.goal = alliance == Alliance.RED? redGoal : blueGoal;

        drivetrain = new Drivetrain(hardwareMap);
        turret = new Turret(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);

        drivetrain.follower.setStartingPose(startPose);

        CommandScheduler.getInstance().registerSubsystem(drivetrain, turret, intake, shooter);
    }

    public static double getHeading(){
        return Math.toDegrees(Snoopy.drivetrain.follower.getHeading());
    }

    public static void update(Gamepad gamepad){
        drivetrain.follower.update();
        turret.update();

        if(Snoopy.matchState == MatchState.TELEOP){
            Snoopy.intake.setPower(gamepad.right_trigger - gamepad.left_trigger);
            Snoopy.drivetrain.robotCentric(gamepad);
        }
    }
}
