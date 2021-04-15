package org.firstinspires.ftc.teamcode.opmodes.year2020.testing.M1RC34_v3;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Functions.Constants;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.plateMinPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.rotateMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.rotateMinPos;
import static org.firstinspires.ftc.teamcode.Functions.robotGrabbyThings.moveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotGrabbyThings.rotateClaw;
import static org.firstinspires.ftc.teamcode.Functions.robotGrabbyThings.slashKill;
import static org.firstinspires.ftc.teamcode.Functions.robotGrabbyThings.useClaw;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.move;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.strafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.turn;

@TeleOp(name="eltest", group="Testing")
public class M1RC34_v3 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        float platePos= plateMinPos, rotatePos=rotateMaxPos, clawPos=clawMinPos;

        DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H1Motor1_FR = hardwareMap.get(DcMotor.class, "H1Motor1_FR");
        DcMotor H1Motor2_BL = hardwareMap.get(DcMotor.class, "H1Motor2_BL");
        DcMotor H1Motor3_BR = hardwareMap.get(DcMotor.class, "H1Motor3_BR");

        DcMotor H2Motor0_Arm = hardwareMap.get(DcMotor.class, "H2Motor0_Arm");
        Servo H2Servo0_PlateLeft = hardwareMap.get(Servo.class, "H2Servo0_PlateLeft");
        Servo H2Servo1_PlateRight = hardwareMap.get(Servo.class, "H2Servo1_PlateRight");

        Servo H2Servo2_RotateClaw = hardwareMap.get(Servo.class, "H2Servo2_RotateClaw");
        Servo H2Servo3_Claw = hardwareMap.get(Servo.class, "H2Servo3_Claw");

        //ModernRoboticsI2cRangeSensor ultraSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "ultraSensor");

        H1Motor0_FL.setPower(0);
        H1Motor1_FR.setPower(0);
        H1Motor2_BL.setPower(0);
        H1Motor3_BR.setPower(0);

        H2Motor0_Arm.setPower(0);
        H2Servo0_PlateLeft.setPosition(1-plateMinPos);
        H2Servo1_PlateRight.setPosition(plateMinPos);
        H2Servo2_RotateClaw.setPosition(rotateMaxPos);
        H2Servo3_Claw.setPosition(clawMinPos);

        waitForStart();

        boolean slow=false;
        while (opModeIsActive() && !gamepad1.x)
        {
            //region Speed & Init
            if(gamepad1.right_bumper) slow=!slow;
            float speedX=gamepad1.left_stick_x, speedY=gamepad1.left_stick_y, speedTurn=gamepad1.right_stick_x;
            speedX = slow?speedX*0.75f:speedX;
            speedY = slow?speedY*0.75f:speedY;
            speedTurn = slow?speedTurn*0.75f:speedTurn;

            H1Motor0_FL.setPower(0);
            H1Motor1_FR.setPower(0);
            H1Motor2_BL.setPower(0);
            H1Motor3_BR.setPower(0);
            //endregion
            //region Movement
            if(!gamepad1.a) {
                move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedY);
                strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedX);
                turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, speedTurn);
            }
            else {
                move(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                strafe(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
                turn(H1Motor0_FL, H1Motor1_FR, H1Motor2_BL, H1Motor3_BR, 0);
            }
            //endregion

            moveArm(H2Motor0_Arm, gamepad2.dpad_up, gamepad2.dpad_down);
            rotatePos=rotateClaw(H2Servo2_RotateClaw, rotatePos, gamepad2);
            clawPos=useClaw(H2Servo3_Claw, clawPos, gamepad2);
            platePos = slashKill(H2Servo0_PlateLeft, H2Servo1_PlateRight, gamepad2.right_stick_y, platePos);

            /*telemetry.addData("Plate Left: ", H2Servo0_PlateLeft.getPosition());
            telemetry.addData("Plate Right: ", H2Servo1_PlateRight.getPosition());
            telemetry.addData("Claw position: ", clawPos);
            telemetry.addData("Rotate position: ", rotatePos);
            telemetry.addData("Rotation position: ", H2Motor0_Arm.getCurrentPosition());
            telemetry.addData("Sensor range: ", ultraSensor.getDistance(DistanceUnit.CM));

            telemetry.update();*/
        }
    }
}