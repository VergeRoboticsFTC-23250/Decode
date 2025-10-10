package org.firstinspires.ftc.teamcode.scarlet;

public class PDController {
    private double kP;
    private double kD;
    private double tolerance;
    private double target;

    private double lastError = 0;
    private double lastTime = 0;

    public PDController(double kP, double kD) {
        this.kP = kP;
        this.kD = kD;
    }

    public double calculate(double pos) {
        double error = target - pos;
        double currentTime = System.currentTimeMillis();

        if (lastTime == 0) {
            lastTime = currentTime;
            lastError = error;
            return kP * error;
        }

        double deltaTime = (currentTime - lastTime) / 1000.0;
        double derivative = (error - lastError) / deltaTime;

        lastError = error;
        lastTime = currentTime;

        return (kP * error) + (kD * derivative);
    }

    public boolean atTarget(double pos) {
        return Math.abs(target - pos) < tolerance;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setKP(double kP) {
        this.kP = kP;
    }

    public void setKD(double kD) {
        this.kD = kD;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public double getTarget() {
        return target;
    }
}
