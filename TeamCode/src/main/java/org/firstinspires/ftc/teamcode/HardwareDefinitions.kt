package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.hardware.MotorAssembly
import addonovan.kftc.hardware.MotorType
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * Created by gaarj on 10/17/2016.
 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft = get< Motor >( "motor_left" ).setAssembly( MotorAssembly( MotorType.ANDYMARK) );
    val motorRight = get< Motor >( "motor_right" ).setAssembly( MotorAssembly( MotorType.ANDYMARK) );

    // The vertical conveyor belt motor
    val motorConv: Motor = get( "motor_conv" );

    // The motor that powers the flicking mechanism to launch the balls
    val motorFlick: Motor = get( "motor_flick" );
}
