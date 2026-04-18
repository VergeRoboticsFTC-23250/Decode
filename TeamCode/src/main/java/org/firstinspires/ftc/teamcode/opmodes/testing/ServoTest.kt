package org.firstinspires.ftc.teamcode.opmodes.testing

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.seattlesolvers.solverslib.command.CommandOpMode
import org.firstinspires.ftc.teamcode.lib.subsystems.Turret
import org.firstinspires.ftc.teamcode.lib.util.Context

@TeleOp
@Configurable
class ServoTest: CommandOpMode(){
    companion object {
        @JvmField var angle = 0.0
    }

    lateinit var c: Context
    lateinit var turret: Turret
    override fun initialize() {
        c = Context(hardwareMap)
        turret = Turret(c)
    }

    override fun run() {
        turret.setAngle(angle)
        super.run()
    }
}