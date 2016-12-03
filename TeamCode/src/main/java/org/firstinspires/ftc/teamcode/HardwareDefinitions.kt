package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.hardware.ToggleServo
import addonovan.kftc.util.MotorAssembly
import addonovan.kftc.util.MotorGroup
import addonovan.kftc.util.MotorType
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo

/** Created by gaarj on 10/17/2016 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft: Motor by get< Motor >( "motor_left" );
    val motorRight: Motor by get< Motor >( "motor_right" );
    val motorFront: Motor by get< Motor >( "motor_front" );
    val motorBack: Motor by get< Motor >( "motor_back" );

    val lateralMotors: MotorGroup by lazy()
    {
        MotorGroup( motorLeft, motorRight );
    }

    val medialMotors: MotorGroup by lazy()
    {
        MotorGroup( motorFront, motorBack );
    }

    fun init()
    {
        // reverse here
        motorRight.direction = DcMotorSimple.Direction.REVERSE;
        motorBack.direction = DcMotorSimple.Direction.REVERSE;
    }
}
