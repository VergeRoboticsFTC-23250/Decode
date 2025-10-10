package org.firstinspires.ftc.teamcode.scarlet;

public class PowerController {
    private double kPow;
    private double target;
    private double tolerance;
    private double power;

    public PowerController(double kSq, double tolerance, double power) {
        this.kPow = kSq;
        this.tolerance = tolerance;
        this.power = power;
    }

    public double calculate(double pos) {
        double error = target - pos;
        double output = Math.pow(Math.abs(error * kPow), power);
        return Math.copySign(output, error);
    }

    public boolean atTarget(double pos) { return Math.abs(target - pos) < tolerance; }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setPower(double power) { this.power = power; }

    public void setTolerance(double tolerance) { this.tolerance = tolerance; }

    public void setKSq(double kSq) {
        this.kPow = kSq;
    }

    public double getTarget() {
        return target;
    }
}