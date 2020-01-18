package org.firstinspires.ftc.robotcontroller.external.samples;



import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@Autonomous
public class AutonomousStraightBlue extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor armMotor;
    private Servo clawServo;
    private DcMotor middleWheel;
    DistanceSensor sensorDistance;
    ColorSensor sensorColor;


    @Override
    public void runOpMode() {
        ElapsedTime opmodeRunTime = new ElapsedTime();
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        middleWheel = hardwareMap.get(DcMotor.class, "middleWheel");
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double armRaisedState = 0; //arm is in neutral state at the start
        int armPositionTarget = 0;
        while(opmodeRunTime.seconds()<1.0){
            changeGrab(-1);
        }
        setToNormal();
        opmodeRunTime.reset();
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds()<0.3){
            driveForward(0.75);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds()<5.0){
            changeArm(1.0);
        }
        setToNormal();
        opmodeRunTime.reset();
        while(opmodeRunTime.seconds()<1.0){
            changeGrab(1);
        }
        setToNormal();
        opmodeRunTime.reset();
        while (opmodeRunTime.seconds()<0.5){
            driveBackward(0.75);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds()<6.0){
            driveLeft(0.75);
        }
        setToNormal();
        opmodeRunTime.reset();
        while(opmodeRunTime.seconds()<1.0){
            changeGrab(-1);
        }
        setToNormal();
        opmodeRunTime.reset();
        colorSenseBlue();
    }

        public void driveBackward ( double power){
            leftWheel.setPower(power);
            rightWheel.setPower(-power);
            telemetry.addData("Wheel Power", leftWheel.getPower());
            telemetry.addData("Right Wheel Power", rightWheel.getPower());
            telemetry.addData("is Moving", "Backward");
            telemetry.update();
        }
        public void driveForward ( double power){
            leftWheel.setPower(-power);
            rightWheel.setPower(power);
            telemetry.addData("Left Wheel Power", leftWheel.getPower());
            telemetry.addData("Right Wheel Power", rightWheel.getPower());
            telemetry.addData("is Moving", "Forward");
            telemetry.update();
        }
        public void driveRight ( double power){
            middleWheel.setPower(power);
            telemetry.addData("Wheel Power", middleWheel.getPower());
            telemetry.addData("is Turning", "Right");
            telemetry.update();
        }
        public void driveLeft ( double power){
            middleWheel.setPower(-power);
            telemetry.addData("Wheel Power", middleWheel.getPower());
            telemetry.addData("is Turning", "Right");
            telemetry.update();
        }
        public void changeArm ( double power){
            armMotor.setPower(power);
            telemetry.addData("arm is", "moving");
            telemetry.update();
        }
        public void changeGrab(double distance){
            clawServo.setPosition(distance);
            double clawServoPos = distance;
            telemetry.addData("clawPos", clawServoPos);
            telemetry.addData("ClawDir", "Moving");
            telemetry.update();
        }
        public void setToNormal () {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
            armMotor.setPower(0);
        }
        public void colorSenseBlue () {
            // hsvValues is an array that will hold the hue, saturation, and value information.
            float hsvValues[] = {0F, 0F, 0F};

            // values is a reference to the hsvValues array.
            final float values[] = hsvValues;

            // sometimes it helps to multiply the raw RGB values with a scale factor
            // to amplify/attentuate the measured values.
            final double SCALE_FACTOR = 255;
            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)
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
            telemetry.update();
            while(sensorColor.blue()<100) {
            driveRight(0.45);
            }
        }
    }



