package org.firstinspires.ftc.teamcode

import addonovan.kftc.KOpMode
import addonovan.kftc.Task

/**
 * Created by gaarj on 10/17/2016.
 */

class TeleOp: KOpMode()
{
    companion object : HardwareDefinitions();

    override fun loop()
    {
        // Basic forward and backward movement and rotation
        motorLeft.power = Gamepad1.left_stick_y.toDouble() + Gamepad1.left_stick_x.toDouble();
        motorRight.power = Gamepad1.left_stick_y.toDouble() - Gamepad1.left_stick_x.toDouble();


        // Use the DPad up and down keys to control movement of the vertical conveyor belt
        if (Gamepad1.dpad_down)
        {
            motorConv.power = -1.0;
        } else if (Gamepad1.dpad_up)
        {
            motorConv.power = 1.0;
        } else
        {
            motorConv.power = 0.0;
        }

        if (Gamepad1.right_bumper)
        {
            motorFlick.power = 1.0;
        } else
        {
            motorFlick.power = 0.0;
        }
    }
}