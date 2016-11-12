package org.firstinspires.ftc.teamcode

import addonovan.kftc.hardware.HardwareDefinition
import addonovan.kftc.hardware.Motor
import addonovan.kftc.hardware.ToggleServo
import addonovan.kftc.util.MotorAssembly
import addonovan.kftc.util.MotorType
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.Servo

/** Created by gaarj on 10/17/2016 */

abstract class HardwareDefinitions : HardwareDefinition()
{
    // Define the drive motors
    val motorLeft = get< Motor >( "motor_left" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );
    val motorRight = get< Motor >( "motor_right" ).setAssembly( MotorAssembly( MotorType.NEVEREST_40 ) );

    // The winch motors
    val motorWinch1: Motor = get( "motor_winch1" );
    val motorWinch2: Motor = get( "motor_winch2" );

    // The color sensing facing the ground
    val CSGround: ColorSensor = get( "colorSensorGround" );

    // The color sensing facing out the side of the robot
    val CSWall: ColorSensor = get( "colorSensorSide" );
}
