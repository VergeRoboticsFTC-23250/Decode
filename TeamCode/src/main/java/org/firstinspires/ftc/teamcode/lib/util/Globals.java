package org.firstinspires.ftc.teamcode.lib.util;

import android.graphics.Point;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Globals {
    private static Globals instance;
    private static final Point GOAL_POINT_RED = new Point(144,144);
    private static final Point GOAL_POINT_BLUE = new Point(0,144);
    private static final Pose START_POSE_RED = new Pose(117,127,0);
    private static final Pose START_POSE_BLUE = START_POSE_RED.mirror();
    public enum Alliance{ RED, BLUE }
    public enum MatchState{ AUTO, TELE }
    public final HardwareMap hMap;
    public final MatchState matchState;
    public final Alliance alliance;
    public final Point goalPoint;
    public final Pose startPose;
    public Globals (HardwareMap hMap, MatchState matchState, Alliance alliance) {
        this.hMap = hMap;
        this.matchState = matchState;
        this.alliance = alliance;

        this.goalPoint = alliance == Alliance.RED? GOAL_POINT_RED : GOAL_POINT_BLUE;
        this.startPose = alliance == Alliance.RED? START_POSE_RED : START_POSE_BLUE;

        Globals.instance = this;
    }
    public Globals(HardwareMap hMap){
        this(hMap, MatchState.TELE, Alliance.RED);
    }

    public static Globals getInstance() {
        return instance;
    }
}
