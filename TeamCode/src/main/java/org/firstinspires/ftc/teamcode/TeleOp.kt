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

        // Handles the linear slide moving up and down with the winch motors
        if ( Gamepad1.dpad_up )
        {
            motorWinch1.power = 1.0;
            motorWinch2.power = 1.0;
        } else if ( Gamepad1.dpad_down )
        {
            motorWinch1.power = -1.0;
            motorWinch2.power = -1.0;
        } else
        {
            motorWinch1.power = 0.0;
            motorWinch2.power = 0.0;
        }
    }
}