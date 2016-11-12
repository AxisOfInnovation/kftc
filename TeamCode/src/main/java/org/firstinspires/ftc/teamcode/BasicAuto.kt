package org.firstinspires.ftc.teamcode

import addonovan.kftc.KLinearOpMode
import addonovan.kftc.KOpMode
import addonovan.kftc.Task
import addonovan.kftc.TaskManager
import addonovan.kftc.util.Interval
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

/**
 * Created by gaarj on 10/24/2016.
 * Autonomous test file!
 * Just making sure I understand how to do this
 */
@Autonomous( name = "MoveForward" )
class BasicAuto : KOpMode()
{
    companion object : HardwareDefinitions();

    /** A configurable value for the initial waitTime before the autonomous runs */
    private val waitTime = get( "waitTime", 0L );

    private val speed = get( "moveSpeed", 1.0 );

    private val duration = get( "duration", 1000 );

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
                motorLeft.power = speed;
                motorRight.power = speed;
            } else
            {
                motorLeft.power = -speed;
                motorRight.power = -speed;
            }
        } else
        {
            if (dir)
            {
                motorLeft.power = -speed;
                motorRight.power = speed;
            } else
            {
                motorLeft.power = speed;
                motorRight.power = -speed;
            }
        }
    }

    val task = object : Task
    {
        lateinit var firstInterval: Interval;

        override fun onStart()
        {
            firstInterval = Interval( System.currentTimeMillis() + waitTime, duration );
        }

        override fun tick()
        {
            move( true, false );
        }

        override fun isFinished(): Boolean
        {
            return !firstInterval.isActive();
        }

        override fun onFinish() {
            motorLeft.power = 0.0;
            motorRight.power = 0.0;
        }
    }

    private var wasEnqueued = false;

    override fun loop()
    {
        if ( !wasEnqueued )
        {
            TaskManager.registerTask( task, "auto1" );
            wasEnqueued = true;
        }
    }
}