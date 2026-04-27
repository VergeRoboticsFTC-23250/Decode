package org.firstinspires.ftc.teamcode.lib.util;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Globals {
    private static Globals instance;
    private static final Pose GOAL_POSE_RED = new Pose(144,144);
    private static final Pose GOAL_POSE_BLUE = new Pose(0,144);
    private static final Pose START_POSE_RED = new Pose(117,127,0);
    private static final Pose START_POSE_BLUE = START_POSE_RED.mirror();
    private static final double TURRET_OFFSET_FAR_RED = 3;
    private static final double TURRET_OFFSET_FAR_BLUE = -TURRET_OFFSET_FAR_RED;
    public enum Alliance{ RED, BLUE }
    public enum MatchState{ AUTO, TELE }
    public final HardwareMap hMap;
    public final Telemetry telemetry;
    public final MatchState matchState;
    public final Alliance alliance;
    public final Pose goalPose;
    public Pose startPose;
    public final double turretOffsetFar;
    public Globals (CommandOpMode opMode, Alliance alliance) {
        this.hMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.matchState = opMode.getClass().isAnnotationPresent(Autonomous.class)? MatchState.AUTO : MatchState.TELE;
        this.alliance = alliance;

        this.goalPose = alliance == Alliance.RED? GOAL_POSE_RED : GOAL_POSE_BLUE;
        this.startPose = alliance == Alliance.RED? START_POSE_RED : START_POSE_BLUE;
        this.turretOffsetFar = alliance == Alliance.RED? TURRET_OFFSET_FAR_RED : TURRET_OFFSET_FAR_BLUE;

        Globals.instance = this;
    }
    public Globals(CommandOpMode opMode){
        this(opMode, Alliance.RED);
    }

    public static Globals getInstance() {
        return instance;
    }
}
