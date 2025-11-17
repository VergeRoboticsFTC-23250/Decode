package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@TeleOp
public class BulkReading extends OpMode {
    public Motor enc1;
    public Motor enc2;
    public Motor.Encoder encoder1;
    public Motor.Encoder encoder2;

    private long lastLoopTime = 0;
    private double loopHz = 0;
    private double loopMs = 0;

    // Rolling average buffer
    private static final int AVERAGE_WINDOW = 20;
    private final Deque<Double> loopTimes = new ArrayDeque<>();

    @Override
    public void init() {
        enc1 = new Motor(hardwareMap, "enc1");
        enc2 = new Motor(hardwareMap, "enc2");
        encoder1 = enc1.encoder;
        encoder2 = enc2.encoder;
        encoder1.reset();
        encoder2.reset();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        lastLoopTime = System.nanoTime();
    }

    @Override
    public void loop() {
        long currentTime = System.nanoTime();
        double dt = (currentTime - lastLoopTime) / 1e6; // ms
        lastLoopTime = currentTime;

        // Update rolling average
        loopTimes.addLast(dt);
        if (loopTimes.size() > AVERAGE_WINDOW) {
            loopTimes.removeFirst();
        }

        // Compute average dt
        double avgDt = 0;
        for (double t : loopTimes) {
            avgDt += t;
        }
        avgDt /= loopTimes.size();

        loopMs = avgDt;
        loopHz = 1000.0 / avgDt;

        telemetry.addData("enc1", encoder1.getPosition());
        telemetry.addData("enc2", encoder2.getPosition());
        telemetry.addData("Loop Time (ms)", String.format("%.2f", loopMs));
        telemetry.addData("Loop Rate (Hz)", String.format("%.1f", loopHz));
    }
}
