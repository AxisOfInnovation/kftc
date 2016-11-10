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

    /** The time for the initial forward movement */
    private val move1 = get( "initialMovement", 1000L );

    /** The time for rotation to face perpendicular to the beacons */
    private val move2 = get( "firstRotation", 1000L );

    /** The time taken to move either forward or backward slightly to align with the correct beacon button */
    private val move3 = get( "adjustmentMovement", 1000L );

    /** The time for rotation to face the beacons after a line sensing stage */
    private val move4 = get( "secondRotation", 1000L );

    /** The time for ramming into the beacon */
    private val move5 = get( "hitBeaconTime", 1000L );

    /** The time for backing up from the beacon */
    private val move6 = get( "hitBeaconTime", 1000L );

    /** The number of times the robot should slam into the beacon */
    private val slamMax = get( "slamCount", 2L );

    /** Have we hit no beacons - false - or one beacon - true - so far? */
    private var hitBeacon = false;

    /** The number of times the robot has slammed into the beacon */
    private var slamCount = 0L;

    /** Hue, saturation, and value information of the color sensor */
    private var hsvValues = floatArrayOf(0f, 0f, 0f);

    /** Was the first half of the beacon passed red (true) or blue (false)? */
    private var beaconColor = false;

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

    // Move forward towards the beacons
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

    // Turn to face perpendicular to the beacon fronts
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
            startLineSensing();
            //TaskManager.registerTask( lineSensingInterim, "lineSensingInterim" );
        }
    }

    // Move until the robot hits a white line
    private val lineSensingInterim = object : Task
    {
        // Move towards the beacons with line sensing
        override fun onStart()
        {
            // Convert the RGB values of the color sensor to HSV values
            Color.RGBToHSV( CSGround.red() * 8, CSGround.green() * 8, CSGround.blue() * 8, hsvValues);
        }

        override fun tick()
        {
            // Keep moving until you hit a line
            move(true, false);
        }

        override fun isFinished(): Boolean
        {
            // Is the first half of the beacon blue or red?
            if ( CSWall.blue() > CSWall.red() ) beaconColor = false;
            else beaconColor = true;

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

    // Move either forward or backward slightly to align with the correct button
    private val task3 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move3 );
        }

        override fun tick()
        {
            // Move backward if the color we want is behind us
            // Move forward if the color we want is not behind us
            move( !( team == beaconColor ), false );
        }

        override fun isFinished(): Boolean
        {
            return !interval.isActive();
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();
            TaskManager.registerTask( task4, "task4" );
        }
    }

    // Turn to face the beacon
    private val task4 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move4 );
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
            TaskManager.registerTask( task5, "task5" );
        }
    }

    // Move forward to press the beacon
    private val task5 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move5 );
        }

        override fun tick()
        {
            // Move towards and slam into the beacon! :)
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
            TaskManager.registerTask( task6, "task6" );
        }
    }

    // Move back away from the beacon
    private val task6 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            interval = Interval( move6 );
        }

        override fun tick()
        {
            // Move back slightly -- less than how much the robot moves forward
            move( false, false );
        }

        override fun isFinished(): Boolean
        {
            return !interval.isActive();
        }

        override fun onFinish()
        {
            motorLeft.brake();
            motorRight.brake();

            // If we haven't slammed into the beacon enough, keep going!
            if ( slamCount < slamMax )
            {
                slamCount++;
                repeatRamming();
                // TaskManager.registerTask( task5, "task5" );
            } else // We've done it enough, move on with your life
            {
                slamCount = 0;
                TaskManager.registerTask( task7, "task7" );
            }
        }
    }

    // Rotate back to face perpendicular to the beacon fronts
    private val task7 = object : Task
    {
        private lateinit var interval: Interval;

        override fun onStart()
        {
            // Move 4 involved rotating 90 degrees, so does this
            interval = Interval( move4 );
        }

        override fun tick()
        {
            // Rotate back to the be on a parallel path to the beacons
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

            // If this is our first beacon to hit, go back to the line sensing stage
            if ( !hitBeacon )
            {
                hitBeacon = true;
                startLineSensing();
                // TaskManager.registerTask( lineSensingInterim, "lineSensingInterim2" );
            } else
            {
                // We're done! :)
            }
        }
    }

    // Register the lineSensingInterim task
    fun startLineSensing()
    {
        TaskManager.registerTask( lineSensingInterim, "lineSensingInterim" );
    }

    // Re-register the ramming-the-beacon task to do it again
    fun repeatRamming()
    {
        TaskManager.registerTask( task5, "task5" );
    }

    // Register the first task :)
    override fun start()
    {
        TaskManager.registerTask( task1, "task1" );
    }

    // No need to loop anything here, this is all done in a linear progression
    override fun loop() { }
}