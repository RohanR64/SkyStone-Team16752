package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class NormalDriveV1 extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor armMotor;
    private Servo armServo;
    private Servo clawServo;

    @Override
    public void runOpMode() {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        armServo = hardwareMap.get(Servo.class, "armServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double wheelPowerTarget = 0;
        while (opModeIsActive()) {

            //Driving Area
            if (this.gamepad1.right_stick_x > 0)
            {
                wheelPowerTarget = this.gamepad1.right_stick_x;
                leftWheel.setPower(-wheelPowerTarget);
                rightWheel.setPower(-wheelPowerTarget);
                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", leftWheel.getPower());
                telemetry.addData("Right Wheel Power", rightWheel.getPower());
                telemetry.addData("is Turning", "Left");
                telemetry.update();
            }
            else if (this.gamepad1.right_stick_x < 0)
            {
                wheelPowerTarget = this.gamepad1.right_stick_x;
                leftWheel.setPower(wheelPowerTarget);
                rightWheel.setPower(wheelPowerTarget);
                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", -leftWheel.getPower());
                telemetry.addData("Right Wheel Power", -rightWheel.getPower());
                telemetry.addData("is Turning", "Right");
                telemetry.update();
            }
            else if (this.gamepad1.right_stick_x == 0)
            {
                wheelPowerTarget = this.gamepad1.left_stick_y;
                leftWheel.setPower(wheelPowerTarget);
                rightWheel.setPower(-wheelPowerTarget);
                telemetry.addData("Status", "Running");
                telemetry.addData("Left Wheel Power", leftWheel.getPower());
                telemetry.addData("Right Wheel Power", -rightWheel.getPower());
                if (this.gamepad1.left_stick_y > 0) {
                    telemetry.addData("Forward/Backwards", "Backwards");
                } else if (this.gamepad1.left_stick_y == 0) {
                    telemetry.addData("Forward/Backwards", "No Forward/Backward Movement");
                } else if (this.gamepad1.left_stick_y < 0) {
                    telemetry.addData("Forward/Backwards", "Forwards");
                }
                telemetry.update();





            }
        }
    }
}
