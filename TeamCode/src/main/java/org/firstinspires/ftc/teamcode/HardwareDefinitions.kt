package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.util.MotorAssembly
import addonovan.kftc.util.MotorType
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * Created by gaarj on 10/17/2016.
 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft = get< Motor >( "motor_left" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );
    val motorRight = get< Motor >( "motor_right" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );

    // The catapult launching motor
    val motorCatapult: Motor = get( "motor_catapult" );
}
