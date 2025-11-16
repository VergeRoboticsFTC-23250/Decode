package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Snoopy {
    public enum MatchState {
        AUTO,
        TELEOP
    }
    public enum Alliance {
        RED,
        BLUE
    }

    MatchState matchState;
    Alliance alliance;

    public Pose startPose = new Pose(24, 126.5, Math.toRadians(90)); // blue side
    public double turretTrackX;
    public double turretTrackY;

    public Snoopy(HardwareMap hardwareMap, MatchState state, Alliance ally) {
        matchState = state;
        alliance = ally;

        if (matchState == MatchState.AUTO && alliance == Alliance.BLUE) {
            turretTrackX = 0;
            turretTrackY = 144;
        } else if (matchState == MatchState.AUTO && alliance == Alliance.RED) {
            startPose = startPose.mirror();
            turretTrackX = 144;
            turretTrackY = 144;
        }
    }
}
