package org.firstinspires.ftc.teamcode.lib.subsystems.shooter;

import com.acmerobotics.dashboard.config.Config;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.lib.util.Globals;
import org.firstinspires.ftc.teamcode.lib.util.LinearMapper;

@Config
public class Hood extends SubsystemBase {
    private final ServoEx hood;
    public static double min = 0.395, max = 0.87;
    LinearMapper mapper;

    public Hood(Globals g){
        hood = new ServoEx(g.hMap, "sh4");
        hood.setInverted(true);
        mapper = new LinearMapper(0, 1, min, max);
    }

    public void set(double percent){
        hood.set(mapper.map(percent));
    }
}
