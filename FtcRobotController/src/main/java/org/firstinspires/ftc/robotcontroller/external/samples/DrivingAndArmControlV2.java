package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class DrivingAndArmControlV2 extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor armMotor;
    private DcMotor middleWheel;
    private Servo clawRotateServo;
    private Servo clawServo;

    @Override
    public void runOpMode() {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        middleWheel = hardwareMap.get(DcMotor.class, "middleWheel");
        //armServo = hardwareMap.get(Servo.class, "armServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
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
            leftWheel.setPower(DrivePower);
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
            middleWheel.setPower(StrafePower);
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
            telemetry.update();

        //Rotational Motion
            if (!TranslationalMvmt)
            {
                RotatePower = this.gamepad1.right_stick_x;
                leftWheel.setPower(-RotatePower);
                rightWheel.setPower(RotatePower);
                if (this.gamepad1.right_stick_x>0)
                {
                    telemetry.addData("is Rotating","Right/Clockwise");
                }
                else if (this.gamepad1.right_stick_x<0)
                {
                    telemetry.addData("is Rotating","Left/Counterclockwise");
                }
            }
            telemetry.update();

        //Arm Motion
            while (this.gamepad2.left_stick_y>0)
            {
                    armMotor.setPower(0.1);
                    telemetry.addData("arm is", "being raised");
            }
            while (this.gamepad2.left_stick_y<0)
            {
                    armMotor.setPower (-.1);
                    telemetry.addData("arm is", "being raised");
            }
            while(this.gamepad2.left_stick_y==0)
            {
                armMotor.setPower(0);
                telemetry.addData("arm is", "not moving");
            }
            telemetry.update();

        //Claw Motion
            if (gamepad2.right_stick_y>0)
            {
                clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                double clawServoPos = clawServo.getPosition();
                telemetry.addData("clawPos", clawServoPos);
                telemetry.addData("ClawDir","Grabbing");
            }
            else if (gamepad2.right_stick_y<0)
            {
                clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                double clawServoPos = clawServo.getPosition();
                telemetry.addData("clawPos", clawServoPos);
                telemetry.addData("clawDir", "Releasing" );
            }
            telemetry.update();

        //Claw Rotation
            while (this.gamepad2.right_stick_x>0)
            {
                ClawRotateServoPos += 0.05;
                telemetry.addData("claw rotation", "clockwise");
            }
            while (this.gamepad2.right_stick_x<0)
            {
                ClawRotateServoPos -= 0.05;
                telemetry.addData("claw rotation", "counterclockwise");
            }
            while(this.gamepad2.right_stick_x==0)
            {
                telemetry.addData("claw rotation", "none");
            }
            clawRotateServo.setPosition(ClawRotateServoPos);
            telemetry.update();

        }
    }
}

