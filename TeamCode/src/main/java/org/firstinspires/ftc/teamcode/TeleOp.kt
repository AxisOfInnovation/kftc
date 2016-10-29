package org.firstinspires.ftc.teamcode

import addonovan.kftc.KOpMode
import addonovan.kftc.Task
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

/**
 * Created by gaarj on 10/17/2016.
 */

@TeleOp( name = "TeleOp" )
class TeleOp: KOpMode()
{
    companion object : HardwareDefinitions();

    override fun loop()
    {
        // Basic forward and backward movement and rotation
        motorLeft.power = Gamepad1.left_stick_y.toDouble() + Gamepad1.left_stick_x.toDouble();
        motorRight.power = Gamepad1.left_stick_y.toDouble() - Gamepad1.left_stick_x.toDouble();

        // Use the pen servo :)
        // Toggle with the b button
        if ( Gamepad1.b ) servoPen.toggle();
    }
}