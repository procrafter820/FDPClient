package net.skiddermc.fdpclient.features.module.modules.movement.speeds.verus
import net.skiddermc.fdpclient.features.module.modules.movement.speeds.SpeedMode
import net.skiddermc.fdpclient.utils.MovementUtils
import net.skiddermc.fdpclient.value.BoolValue

class VerusHop2 : SpeedMode("VerusHop2") {

    private val timerBoost = BoolValue("TimerBoost",true)

    private var jumps = 0
    private var lastY = 0.0

    override fun onPreMotion() {

        if (MovementUtils.isMoving()) {
            if (timerBoost.get() && (jumps >= 1)) {
                mc.timer.timerSpeed = if (mc.thePlayer.motionY < 0) { 0.88f } else { 1.25f }
            }

            when {
                mc.thePlayer.onGround -> {
                    MovementUtils.strafe(0.4848f)
                    mc.thePlayer.motionY = (0.42f).toDouble()

                    if (mc.thePlayer.posY == lastY) {
                        jumps++
                    } else {
                        jumps = 0
                    }

                    lastY = mc.thePlayer.posY
                }
                else -> {
                    MovementUtils.strafe(0.3772f)
                }
            }
        }
    }
}