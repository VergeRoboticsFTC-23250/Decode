package org.firstinspires.ftc.teamcode.scarlet;

public class SquidController {
    public double kSq;
    public double target;
    public double tolerance;

    public SquidController(double kSq, double tolerance) {
        this.kSq = kSq;
        this.tolerance = tolerance;
    }

    public double calculate(double pos) { return Math.sqrt(Math.abs((target - pos) * kSq)) * Math.signum(target - pos); }

    public boolean atTarget(double pos) { return Math.abs(target - pos) < tolerance; }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setKSq(double kSq) {
        this.kSq = kSq;
    }

    public double getTarget() {
        return target;
    }
}