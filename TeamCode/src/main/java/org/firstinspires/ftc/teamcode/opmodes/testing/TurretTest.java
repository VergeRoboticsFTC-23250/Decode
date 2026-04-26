package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.lib.subsystems.Turret;
import org.firstinspires.ftc.teamcode.lib.util.Globals;

//@TeleOp
//@Configurable
//@Config
public class TurretTest extends CommandOpMode {

    public static double angle = 0.0;
    public static boolean wrapped = false;

    private Globals g;
    private Turret turret;
//    private GoBildaPinpointDriver pinpoint;

    @Override
    public void initialize() {
        g = new Globals(this);
        turret = new Turret(g);

//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
//        pinpoint.resetPosAndIMU();
//        pinpoint.setHeading(0.0, AngleUnit.RADIANS);
    }

    @Override
    public void run() {
        turret.setAngle(angle);

//        pinpoint.update();
//        double thingy = pinpoint.getHeading(AngleUnit.DEGREES);
//        if (thingy < 0) {
//            thingy += 360;
//        } else if (wrapped) {
//            thingy += 360;
//        }

//        telemetry.addData("pinpoint angle", thingy);
        telemetry.addData("servo pos all", angle);
        telemetry.update();

        super.run();
    }
}