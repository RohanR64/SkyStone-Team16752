package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class BuildingSide extends LinearOpMode {
    private DcMotor leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
    private DcMotor rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
    private DcMotor armMotor = hardwareMap.get(DcMotor.class, "armMotor");
    private Servo clawServo = hardwareMap.get(Servo.class, "clawServo");

    @Override
    public void runOpMode() {
        ElapsedTime opmodeRunTime = new ElapsedTime();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double armRaisedState = 0; //arm is in neutral state at the start
        int armPositionTarget = 0;
        opmodeRunTime.reset();
        while (opmodeRunTime.seconds() < 1.0){
            driveForward(1);
        }
    }

    public void driveForward(double power){
        leftWheel.setPower(power);
        rightWheel.setPower(-power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Moving", "Forward");
        telemetry.update();
    }
    public void driveBackward(double power){
        leftWheel.setPower(-power);
        rightWheel.setPower(power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Moving", "Backward");
        telemetry.update();
    }
    public void turnLeft(double power){
        leftWheel.setPower(-power);
        rightWheel.setPower(-power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Turning", "Left");
        telemetry.update();
    }
    public void turnRight(double power){
        leftWheel.setPower(power);
        rightWheel.setPower(power);
        telemetry.addData("Left Wheel Power", leftWheel.getPower());
        telemetry.addData("Right Wheel Power", rightWheel.getPower());
        telemetry.addData("is Turning", "Right");
        telemetry.update();
    }
    public void closeGrab(){

    }
}