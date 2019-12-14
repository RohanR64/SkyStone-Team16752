package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.sun.tools.doclint.Entity.and;

@TeleOp
public class DrivingAndArmControlV1 extends LinearOpMode {
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
        double armRaisedState = 0; //arm is in neutral state at the start
        int armPositionTarget =0;
        while (opModeIsActive()) {

            //Driving And steering
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

            }
            //arm raising and lowering section
            if (this.gamepad2.dpad_up){
                armPositionTarget+=0.01;
                armMotor.setTargetPosition(armPositionTarget);
                telemetry.addData("arm is","being raised");
                armRaisedState=1;
            }
            else if (this.gamepad2.dpad_down){
                armPositionTarget-=0.01;
                armMotor.setTargetPosition(armPositionTarget);
                telemetry.addData("arm is","being lowered");
                armRaisedState=-1;
            }
            else {
                telemetry.addData("arm is","not being raised or lowered");
                armRaisedState=0;
            }



            //grabber control section
            if (armRaisedState==-1) {
                //if arm is lowered to the ground
                double basePos = 0.58;
                telemetry.addData("Mode", "Lowered Arm");
                if (gamepad2.left_stick_y<0){


                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Down");


                }
                else if (gamepad2.left_stick_y==0){

                    armServo.setPosition(basePos);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Neutral");

                }
                else if (gamepad2.left_stick_y>0) {


                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Up");
                }
                if (gamepad2.right_stick_y>0){


                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("ClawDir","Grabbing");

                }
                else if (gamepad2.right_stick_y==0){

                    clawServo.setPosition(0.65);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Neutral");

                }
                else if (gamepad2.right_stick_y<0) {

                    //armServo.setDirection(Servo.Direction.FORWARD);
                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Releasing");

                }

            }
            else if (armRaisedState==1) {
                //if arm is extended upwards
                double basePos = 0.03;
                telemetry.addData("Mode","Arm Extended Upwards");
                if (gamepad2.left_stick_y<0){


                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Down");


                }
                else if (gamepad2.left_stick_y==0){

                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Neutral");

                }
                else if (gamepad2.left_stick_y>0) {


                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Up");
                }
                if (gamepad2.right_stick_y>0){


                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("ClawDir","Grabbing");

                }
                else if (gamepad2.right_stick_y==0){

                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Neutral");

                }
                else if (armRaisedState==0) {


                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Releasing");

                }

            }
            else {

                double basePos = 0.25;
                telemetry.addData("Mode","Neutral");
                if (gamepad2.left_stick_y<0){


                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Down");


                }
                else if (gamepad2.left_stick_y==0){

                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Neutral");

                }
                else if (gamepad2.left_stick_y>0) {

                    //armServo.setDirection(Servo.Direction.FORWARD);
                    armServo.setPosition(basePos-gamepad2.left_stick_y/3);
                    double armServoPos = armServo.getPosition();
                    telemetry.addData("armPos", armServoPos);
                    telemetry.addData("armDir", "Up");
                }
                if (gamepad2.right_stick_y>0){

                    //armServo.setDirection(Servo.Direction.REVERSE);
                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("ClawDir","Grabbing");

                }
                else if (gamepad2.right_stick_y==0){

                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Neutral");

                }
                else if (gamepad2.right_stick_y<0) {

                    //armServo.setDirection(Servo.Direction.FORWARD);
                    clawServo.setPosition(0.65+gamepad2.right_stick_y/2.5);
                    double clawServoPos = clawServo.getPosition();
                    telemetry.addData("clawPos", clawServoPos);
                    telemetry.addData("clawDir", "Releasing" );

                }
                telemetry.update();
        }
    }
}
}