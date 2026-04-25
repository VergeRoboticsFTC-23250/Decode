package org.firstinspires.ftc.teamcode.lib.subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.HardwareDevice;

public class Brushland implements HardwareDevice {
    DigitalChannel color, prox;

    Brushland(HardwareMap hMap, String color, String prox){
        this.color = hMap.digitalChannel.get(color);
        this.prox = hMap.digitalChannel.get(prox);
    }

    public boolean isBlocked(){
        return color.getState() || prox.getState();
    }

    @Override
    public void disable() {

    }

    @Override
    public String getDeviceType() {
        return "Brushland Labs Color Rangefinder";
    }
}
