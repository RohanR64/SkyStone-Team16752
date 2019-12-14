package org.firstinspires.ftc.robotcontroller.external.samples;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous
public class BuildingSide extends LinearOpMode {
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private Servo clawServo;

    @Override
    public void runOpMode() {
        ElapsedTime opmodeRunTime = new ElapsedTime();
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        telemetry.addData("Status", "Initialized ");
        telemetry.update();
        double wheelPowerTarget = 0;
        double armRaisedState = 0; //arm is in neutral state at the start
        int armPositionTarget =0;
        wheelPowerTarget = 1;
        opmodeRunTime.reset();
        while (opmodeRunTime.seconds() < 1.0) {
            rightWheel.setPower(wheelPowerTarget);
            telemetry.update();
        }
        opmodeRunTime.reset();
        while (opmodeRunTime.seconds() < 3.0) {
            rightWheel.setPower(-wheelPowerTarget);
            leftWheel.setPower(wheelPowerTarget);
            telemetry.update();
        }
        opmodeRunTime.reset();
        while (opmodeRunTime.seconds() < 1.0) {
            leftWheel.setPower(wheelPowerTarget);
            telemetry.update();
        }
        wheelPowerTarget = 0;
        rightWheel.setPower(-wheelPowerTarget);
        leftWheel.setPower(wheelPowerTarget);

    }
}
