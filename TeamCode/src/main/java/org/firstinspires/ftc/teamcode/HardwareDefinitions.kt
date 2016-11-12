package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.hardware.ToggleServo
import addonovan.kftc.util.MotorAssembly
import addonovan.kftc.util.MotorType
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo

/**
 * Created by gaarj on 10/17/2016.
 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft = get< DcMotor >( "motor left" );
    val motorRight = get< DcMotor >( "motor right" );

    init
    {
        motorRight.direction = DcMotorSimple.Direction.REVERSE;
    }

    val motorWinch1 = get< DcMotor >( "motor winch1" );
    val motorWinch2 = get< DcMotor >( "motor winch2" );
}
