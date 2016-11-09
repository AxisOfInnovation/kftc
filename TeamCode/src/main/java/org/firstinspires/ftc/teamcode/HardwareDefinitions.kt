package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.hardware.ToggleServo
import addonovan.kftc.util.MotorAssembly
import addonovan.kftc.util.MotorType
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.Servo

/**
 * Created by gaarj on 10/17/2016.
 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft = get< Motor >( "motor_left" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );
    val motorRight = get< Motor >( "motor_right" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );

    // The servo that handles the bar that encloses the pen for the balls
    val servoPenBaseValue: Servo = get( "servo_pen" );

    // Make the servo into a toggle servo :)
    val servoPen = ToggleServo( servoPenBaseValue );

    val colorSensor: ColorSensor = get( "colorSensor" );
}
