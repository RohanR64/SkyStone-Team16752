package org.firstinspires.ftc.robotcontroller.external.samples;



import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@TeleOp
public class oneControlDriving extends LinearOpMode  {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor armMotor;
    private DcMotor middleWheel;
    private Servo clawServo;
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;

    @Override
    public void runOpMode() {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        middleWheel = hardwareMap.get(DcMotor.class, "middleWheel");

        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // das
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double DrivePower = 0;
        double StrafePower = 0;
        double RotatePower = 0;
        double ClawRotateServoPos = 0;
        boolean TranslationalMvmt = false;
        while (opModeIsActive()) {

            //Translational Motion
            DrivePower = -this.gamepad1.left_stick_y;
            leftWheel.setPower(-DrivePower);
            rightWheel.setPower(DrivePower);
            telemetry.addData("Status", "Running");
            if (this.gamepad1.left_stick_y > 0)
            {
                telemetry.addData("is moving", "Forward");
                TranslationalMvmt =true;
                telemetry.addData("Left Wheel Translational Power", leftWheel.getPower());
                telemetry.addData("Right Wheel Translational Power", rightWheel.getPower());
            }
            else if (this.gamepad1.left_stick_y < 0)
            {
                telemetry.addData("is moving", "Backward");
                TranslationalMvmt =true;
                telemetry.addData("Left Wheel Translational Power", leftWheel.getPower());
                telemetry.addData("Right Wheel Translational Power", rightWheel.getPower());
            }
            StrafePower = this.gamepad1.left_stick_x;
            middleWheel.setPower(-StrafePower);
            if (this.gamepad1.right_stick_x > 0)
            {
                telemetry.addData("is moving", "Right");
                TranslationalMvmt =true;
            }
            else if (this.gamepad1.left_stick_x < 0)
            {
                telemetry.addData("is moving", "left");
                TranslationalMvmt =true;
            }
            if (this.gamepad1.left_stick_y == 0)
            {
                if (this.gamepad2.left_stick_x == 0)
                {
                    TranslationalMvmt =false;
                }
            }
            telemetry.update();

            //Rotational Motion
            if (!TranslationalMvmt)
            {
                RotatePower = this.gamepad1.right_stick_x;
                leftWheel.setPower(-RotatePower);
                rightWheel.setPower(-RotatePower);
                if (this.gamepad1.right_stick_x>0)
                {
                    telemetry.addData("is Rotating","Right/Clockwise");
                }
                else if (this.gamepad1.right_stick_x<0)
                {
                    telemetry.addData("is Rotating","Left/Counterclockwise");
                }
            }
            //telemetry.update();

            //Arm Motion
            if (this.gamepad1.left_trigger>0)
            {
                armMotor.setPower(-1.0);
                telemetry.addData("arm is", "being raised");
            }
            else if (this.gamepad1.right_trigger>0)
            {
                armMotor.setPower (1.0);
                telemetry.addData("arm is", "being raised");
            }
            else
            {
                armMotor.setPower(0);
            }
            telemetry.update();

            //Claw Motion
            if (gamepad1.left_bumper)
            {
                clawServo.setPosition(1);
                double clawServoPos = clawServo.getPosition();
                telemetry.addData("clawPos", clawServoPos);
                telemetry.addData("ClawDir","Grabbing");
            }

            if (gamepad1.right_bumper)
            {
                clawServo.setPosition(-1);
                double clawServoPos = clawServo.getPosition();
                telemetry.addData("clawPos", clawServoPos);
                telemetry.addData("ClawDir","Opening");
            }
            telemetry.update();
            //color
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();
        }

        // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });

        }
    }
