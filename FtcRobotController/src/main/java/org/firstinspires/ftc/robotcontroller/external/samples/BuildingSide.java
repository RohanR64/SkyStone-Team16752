package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class BuildingSide extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor armMotor;
    private Servo clawServo;
    @Override
    public void runOpMode() {
        ElapsedTime opmodeRunTime = new ElapsedTime();
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double armRaisedState = 0; //arm is in neutral state at the start
        int armPositionTarget = 0;
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 1.0){
            driveForward(1);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 1.0){
            driveBackward(1);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 1.0){
            turnLeft(1);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 1.0){
            turnRight(1);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 5.0) {
            changeArm(1);
        }
        opmodeRunTime.reset();
        setToNormal();
        while (opmodeRunTime.seconds() < 1.0) {
            changeArm(-1);
        }
        setToNormal();
    }

    public void driveBackward(double power){
        leftWheel.setPower(power);
        rightWheel.setPower(-power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Moving", "Backward");
        telemetry.update();
    }
    public void driveForward(double power){
        leftWheel.setPower(-power);
        rightWheel.setPower(power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Moving", "Forward");
        telemetry.update();
    }
    public void turnRight(double power){
        leftWheel.setPower(-power);
        rightWheel.setPower(-power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Turning", "Right");
        telemetry.update();
    }
    public void turnLeft(double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Turning", "Left");
        telemetry.update();
    }
    public void changeArm(double power){
        armMotor.setPower(power);
        telemetry.addData("arm is", "moving");
        telemetry.update();
    }
    public void changeGrab(double distance){
        clawServo.setPosition(distance);
        double clawServoPos = distance;
        telemetry.addData("clawPos", clawServoPos);
        telemetry.addData("ClawDir","Moving");
        telemetry.update();
    }
    public void setToNormal(){
        leftWheel.setPower(0);
        rightWheel.setPower(0);
        armMotor.setPower(0);
    }
}