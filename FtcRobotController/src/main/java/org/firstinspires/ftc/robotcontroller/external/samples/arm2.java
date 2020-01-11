package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class arm2 extends LinearOpMode {
    private DcMotor motorTest;

    @Override
    public void runOpMode() {
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double wheelPowerTarget = 0;
        while (opModeIsActive()) {

            //Driving Area
            if (this.gamepad1.right_stick_x > 0) {
                wheelPowerTarget = this.gamepad1.right_stick_x;
                motorTest.setPower(-wheelPowerTarget);

                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", motorTest.getPower());

                telemetry.addData("is Turning", "Left");
                telemetry.update();
            } else if (this.gamepad1.right_stick_x < 0) {
                wheelPowerTarget = this.gamepad1.right_stick_x;
                motorTest.setPower(wheelPowerTarget);

                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", -motorTest.getPower());

                telemetry.addData("is Turning", "Right");
                telemetry.update();
            } else if (this.gamepad1.right_stick_x == 0) {
                wheelPowerTarget = this.gamepad1.left_stick_y;
                motorTest.setPower(wheelPowerTarget);

                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", motorTest.getPower());


                telemetry.update();


            }
        }
    }
}
