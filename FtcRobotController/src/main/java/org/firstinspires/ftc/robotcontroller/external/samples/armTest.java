package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class armTest extends LinearOpMode {

    //private DcMotor armMotor;
    private Servo armServo;
    private Servo clawServo;


    @Override
    public void runOpMode() {

        //armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        armServo = hardwareMap.get(Servo.class, "armServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)


        while (opModeIsActive()) {
            telemetry.addData("Status", "Op Mode Initialized");
            telemetry.update();
            if (gamepad2.left_trigger> 0) {
                //if arm is lowered to the ground
                double basePos = 0.58;
                telemetry.addData("Mode", "Lowered Arm");
                telemetry.update();
                if (gamepad2.left_stick_y<0){

                    //armServo.setDirection(Servo.Direction.REVERSE);
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
                telemetry.update();
            }
            else if (gamepad2.right_trigger> 0) {
                //if arm is extended upwards
                double basePos = 0.03;
                telemetry.addData("Mode","Arm Extended Upwards");
                if (gamepad2.left_stick_y<0){

                    //armServo.setDirection(Servo.Direction.REVERSE);
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
                telemetry.update();
            }
            else {
                //arm is somewhere in between
                double basePos = 0.25;
                telemetry.addData("Mode","Neutral");
                if (gamepad2.left_stick_y<0){

                    //armServo.setDirection(Servo.Direction.REVERSE);
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
                    telemetry.addData("clawDir", "Releasing" );

                }
                telemetry.update();
            }
            }


        }
    }



