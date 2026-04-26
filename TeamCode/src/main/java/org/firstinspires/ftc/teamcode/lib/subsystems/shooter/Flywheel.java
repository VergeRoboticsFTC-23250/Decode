package org.firstinspires.ftc.teamcode.lib.subsystems.shooter;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.lib.util.Globals;

@Config
public class Flywheel extends SubsystemBase {
    Globals g;
    private final MotorEx motor1, motor2;
    public static double p = 0.0036, f = 0.00029;
    private PIDFController controller;
    private double velocity = 0;
    public Flywheel(Globals g){
        this.g = g;
        motor1 = new MotorEx(g.hMap, "ehm0");
        motor2 = new MotorEx(g.hMap, "ehm1");
        motor2.setInverted(true);

        controller = new PIDFController(p, 0, 0, f);
        controller.setSetPoint(velocity);
    }

    @Override
    public void periodic() {
        super.periodic();
        if(controller.getSetPoint() < 1000){
            motor1.set(0);
            motor2.set(0);
        }else{
            velocity = -motor1.encoder.getCorrectedVelocity() / 28.0 * 60 * 0.75;
            double pow = controller.calculate(velocity);
            motor1.set(pow);
            motor2.set(pow);
        }

        g.telemetry.addData("velocity", velocity);

//        controller.setP(p);
//        controller.setF(f);
    }

    public void set(double velocity){
        controller.setSetPoint(velocity);
    }
}
