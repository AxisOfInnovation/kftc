package org.firstinspires.ftc.teamcode

import addonovan.kftc.KOpMode
import addonovan.kftc.Task
import addonovan.kftc.TaskManager
import addonovan.kftc.util.Interval
import android.graphics.Color

/**
 * Created by gaarj on 11/9/2016.
 */
class BeaconAuto : KOpMode()
{
    companion object : HardwareDefinitions();

    /** A configurable value for the initial waitTime before the autonomous runs */
    private val waitTime = get( "waitTime", 0L );

    /**
     * A configurable boolean for which team the player is on
     * True - Red Team, False - Blue Team
     */
    private val team = get( "team", false );

    /** The time for the initial forward movement (first interval) */
    private val move1 = get( "initialMovement", 1000L );

    /** The time for rotation to face perpendicular to the beacons (second interval) */
    private val move2 = get( "firstRotation", 1000L );

    /** The time for rotation to face the beacons after a line sensing stage (third interval) */
    private val move3 = get( "secondRotation", 1000L );

    // Hue, saturation, and value information of the color sensor
    private val hsvValues = floatArrayOf(0f, 0f, 0f);

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

    private val task1 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            // Set the first interval for the autonomous to run
            // after the specified waitTime has run
            interval = Interval( Interval( waitTime ), move1 );
        }

        override fun tick()
        {
            // Move towards the beacons
            move( true, false );
        }

        override fun isFinished(): Boolean
        {
            return !interval.isActive();
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();
            TaskManager.registerTask( task2, "task2" );
        }
    }

    private val task2 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move2 );
        }

        override fun tick()
        {
            // Turn into a path parallel to the beacons
            // Rotate right if on red team, left if on blue team (about 45 degrees)
            move( !team, true );
        }

        override fun isFinished(): Boolean
        {
            return !interval.isActive();
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();
            TaskManager.registerTask( lineSensingInterim, "lineSensing" );
        }
    }

    private val lineSensingInterim = object : Task
    {
        // Move towards the beacons with line sensing
        override fun onStart()
        {
            // Convert the RGB values of the color sensor to HSV values
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        }

        override fun tick()
        {
            // Keep moving until you hit a line
            move(true, false);
        }

        override fun isFinished(): Boolean
        {
            // The lines are white, so they'll have a high value of 'value'
            // Which should be the third element in the array
            return hsvValues[ 2 ] > 200;
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();
            TaskManager.registerTask( task3, "task3" );
        }
    }

    private val task3 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move3 );
        }

        override fun tick()
        {
            // Turn to face the beacons
            // Rotate left if on red team, right if on blue team (about 45 degrees)
            move( team, true );
        }

        override fun isFinished(): Boolean
        {
            return !interval.isActive();
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();
            // TaskManager.registerTask( task4, "lineSensing" );
        }
    }

    override fun start()
    {
        TaskManager.registerTask( task1, "grab object" );
    }

    override fun loop() { }
}