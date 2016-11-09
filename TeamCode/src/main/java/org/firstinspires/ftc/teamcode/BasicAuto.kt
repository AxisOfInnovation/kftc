package org.firstinspires.ftc.teamcode

import addonovan.kftc.KOpMode
import addonovan.kftc.Task
import addonovan.kftc.util.Interval

/**
 * Created by gaarj on 10/24/2016.
 * Autonomous test file!
 * Just making sure I understand how to do this
 */
class BasicAuto : KOpMode()
{
    companion object : HardwareDefinitions();

    /** A configurable value for the initial waitTime before the autonomous runs */
    private val waitTime = get( "waitTime", 0L );

    /**
     * Moves the robot in the specified direction/rotates the robot
     * @param rotate
     *      True - the robot is rotating
     *      False - the robot is moving forward/back
     * @param dir
     *      True - Forward/turning left
     *      False - Backward/turning right
     */
    fun move( dir: Boolean, rotate: Boolean )
    {
        if ( !rotate )
        {
            if (dir)
            {
                motorLeft.power = 1.0;
                motorRight.power = 1.0;
            } else
            {
                motorLeft.power = -1.0;
                motorRight.power = -1.0;
            }
        } else
        {
            if (dir)
            {
                motorLeft.power = -1.0;
                motorRight.power = 1.0;
            } else
            {
                motorLeft.power = 1.0;
                motorRight.power = -1.0;
            }
        }
    }

    override fun loop()
    {

        // Set the first interval for the autonomous to run
        // after the specified waitTime has run
        val firstInterval = Interval( Interval( waitTime ), 10000 );

        while ( firstInterval.isActive() )
        {
            // Move forward
            move( true, false )
        }
    }
}