package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class PedroTest extends OpMode {

    Follower follower;
    Path path;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(0,0,0));
        path = new Path(
                new BezierLine(
                        new Pose(0,0,0),
                        new Pose(20,0,0)
                )
        );
        follower.followPath(path);
    }

    @Override
    public void loop() {
        follower.update();
    }
}
