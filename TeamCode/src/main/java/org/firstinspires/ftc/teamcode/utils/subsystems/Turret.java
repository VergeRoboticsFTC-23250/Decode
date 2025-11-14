package org.firstinspires.ftc.teamcode.utils.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Configurable
public class Turret extends SubsystemBase {
    public Motor turret;

    public double ticksPerDegree = 3.774814815;
    public double degreesPerTick = 1 / ticksPerDegree;
    public double minPos = 0;
    public double maxPos = 360;
    public double offset = 0;
    public double pos = 0;
    // might have to add isAligning boolean to stop PID

    public static double p = 0.05;
    public static double d = 0.001;
    public PIDFController controller = new PIDFController(p, 0, d, 0);
    public double tolerance = 1;
    // measurement is in degrees not ticks

    public Turret(HardwareMap hMap) {
        turret = new Motor(hMap, "turret", Motor.GoBILDA.BARE);
        turret.stopAndResetEncoder();
        turret.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        turret.setInverted(true);
        controller.setTolerance(tolerance);
        controller.setSetPoint(0);
    }

    public void update() {
        pos = turret.getCurrentPosition() * degreesPerTick + offset;
        turret.set(controller.calculate(pos));
    }
    public void update(double target) { // lowk gotta figure out how to work this with pinpoint
        pos = turret.getCurrentPosition() * degreesPerTick + offset;
        turret.set(controller.calculate(pos, target));
    }
    public void setTarget(double target) { controller.setSetPoint(target); }
    public double getTarget() { return controller.getSetPoint(); }

    public void updateFacingPoint(double x, double y, Pose current) {
        double fieldAngle = Math.toDegrees(Math.atan2(
                y - current.getY(),
                x - current.getX()
        ));

        double robotHeading = Math.toDegrees(current.getHeading());

        double turretTarget = fieldAngle - robotHeading;

        update(turretTarget);
    }

}