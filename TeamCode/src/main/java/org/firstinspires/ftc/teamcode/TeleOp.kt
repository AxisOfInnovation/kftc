package org.firstinspires.ftc.teamcode

import addonovan.kftc.KOpMode
import addonovan.kftc.Task
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.HardwareMap

@TeleOp( name = "TeleOp" )
class TeleOp: KOpMode()
{

    companion object : HardwareDefinitions()
    {
        override val hardwareMap: HardwareMap by lazy() { this.hardwareMap };
    }

    override fun init()
    {
        Companion.init();
    }

    override fun tick()
    {
        // Basic forward and backward movement and rotation
        motorLeft.power = gamepad1.left_stick_y.toDouble() + gamepad1.right_stick_x.toDouble();
        motorRight.power = gamepad1.left_stick_y.toDouble() - gamepad1.right_stick_x.toDouble();
        motorFront.power = gamepad1.left_stick_x.toDouble() + gamepad1.right_stick_x.toDouble();
        motorBack.power = gamepad1.left_stick_x.toDouble() - gamepad1.right_stick_x.toDouble();
    }
}