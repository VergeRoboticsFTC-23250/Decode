package org.firstinspires.ftc.teamcode.lib.subsystems

import com.seattlesolvers.solverslib.command.SubsystemBase
import com.seattlesolvers.solverslib.hardware.servos.ServoEx
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup
import org.firstinspires.ftc.teamcode.lib.util.Context

class Turret(c: Context): SubsystemBase() {
    val servos = ServoExGroup(
        ServoEx(c.hMap, "chs0", 355.0),
        ServoEx(c.hMap, "chs2", 355.0),
        ServoEx(c.hMap, "chs3", 355.0)
    )

    fun setAngle(angle: Double){
        servos.set(angle)
    }
}