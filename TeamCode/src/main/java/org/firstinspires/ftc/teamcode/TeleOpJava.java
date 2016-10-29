package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import addonovan.kftc.hardware.ToggleServo;

/**
 * Created by gaarj on 10/28/2016.
 */

@TeleOp( name = "JavaTeleOp" )
public class TeleOpJava extends OpMode
{
    private DcMotorController motorController;

    private DcMotor motorLeft;
    private DcMotor motorRight;

    private ToggleServo penServo;

    @Override
    public void init()
    {
        motorController = hardwareMap.dcMotorController.get("motor_controller");

        // Define the drive motors
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorRight = hardwareMap.dcMotor.get("motor_right");

        // The basis for pen servo
        Servo penServoBase = hardwareMap.servo.get("pen_servo");

        // Set up the pen servo to be a toggle servo
        penServo = new ToggleServo( penServoBase );
    }

    @Override
    public void loop()
    {
        motorLeft.setPower( gamepad1.left_stick_y + gamepad1.left_stick_x );
        motorRight.setPower( gamepad1.right_stick_y - gamepad1.right_stick_x );

        // Use the pen servo :)
        // Toggle with the b button
        if ( gamepad1.b ) penServo.toggle();
    }
}
