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
public class left extends LinearOpMode {
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
        while (opModeIsActive()) {
            while (opmodeRunTime.seconds() > 0 && opmodeRunTime.seconds() < 30) {
                while (opmodeRunTime.seconds()<0.2){
                    leftWheel.setPower(-1.00);
                    rightWheel.setPower(1.00);
                }
                telemetry.addData("time",opmodeRunTime);
                telemetry.update();
                setToNormal();
                colorSenseRed();
            }
            setToNormal();

        }}


    public void setToNormal () {
        leftWheel.setPower(0);
        rightWheel.setPower(0);
        armMotor.setPower(0);
        middleWheel.setPower(0);
    }

    public void colorSenseRed() {
        // hsvValues is an array that will  the hue, saturation, and value information.
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
        telemetry.addData("Red  ", sensorColor.red());
        telemetry.addData("Blue ", sensorColor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.update();
        boolean isDetected = false;

        while (!isDetected) {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
            middleWheel.setPower(1.00);
            telemetry.addData("Wheel Power", middleWheel.getPower());
            telemetry.addData("is Turning", "Right");
            telemetry.update();
            if (sensorColor.blue() > 240) {
                isDetected = true;
                setToNormal();
                telemetry.addData("stop","stop");
                telemetry.update();
            }
            if (sensorColor.red() > 240) {
                isDetected = true;
                setToNormal();
                telemetry.addData("stop","stop");
                telemetry.update();
            }
        }


    }
}