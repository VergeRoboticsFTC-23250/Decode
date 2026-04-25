package org.firstinspires.ftc.teamcode.lib.util;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Context {

    public final HardwareMap hMap;
    public Pose startPose;

    public Context(HardwareMap hMap) {
        this.hMap = hMap;
    }
}