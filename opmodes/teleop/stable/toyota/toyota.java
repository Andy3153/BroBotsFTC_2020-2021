package org.firstinspires.ftc.teamcode.opmodes.teleop.stable.toyota;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

// Our code *communism*
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveTurn;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveZero;

import static org.firstinspires.ftc.teamcode.Functions.robotMovement.collectRing;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.moveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkArmSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkThrowSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkCollectSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotServos.useClaw;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

@TeleOp(name="toyota", group="TeleOp")
public class toyota extends LinearOpMode
{
    @Override
    public void runOpMode() // throws InterruptedException
    {
        //region Declaring variables
        int collectSpeed = 0,
            throwSpeed = 0,
            armSpeed = 0;
        float clawPos = clawMinPos,
              driveMoveSpeed,
              driveStrafeSpeed,
              driveTurnSpeed;
        double servoRingPosInnit = 0.92,
               servoRingPosPush = 0.68;
        //endregion

        //region Declaring motors
        DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H2Motor0_FR = hardwareMap.get(DcMotor.class, "H2Motor0_FR");
        DcMotor H1Motor1_BL = hardwareMap.get(DcMotor.class, "H1Motor1_BL");
        DcMotor H2Motor1_BR = hardwareMap.get(DcMotor.class, "H2Motor1_BR");

        DcMotor H1Motor2_Ramp0 = hardwareMap.get(DcMotor.class, "H1Motor2_Ramp0");
        DcMotor H1Motor3_Ramp1 = hardwareMap.get(DcMotor.class, "H1Motor3_Ramp1");

        DcMotorEx H2Motor2_Throw = hardwareMap.get(DcMotorEx.class, "H2Motor2_Throw");

        DcMotor H2Motor3_Arm =hardwareMap.get(DcMotor.class, "H2Motor3_Arm");

        Servo H2Servo0_Claw = hardwareMap.get(Servo.class, "H2Servo0_Claw");
        Servo H2Servo1_Ring = hardwareMap.get(Servo.class, "H2Servo1_Ring");
        //endregion

        //region Setting Default Servo Positions
        H2Servo0_Claw.setPosition(clawPos);
        H2Servo1_Ring.setPosition(servoRingPosInnit);
        //endregion

        waitForStart();

        while(opModeIsActive())
        {
            //region Driving
            if((driveMoveSpeed = gamepad1.left_stick_y) != 0)
                driveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, driveMoveSpeed);
            else
                driveZero(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR);

            if((driveStrafeSpeed = gamepad1.left_stick_x) != 0)
                driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, driveStrafeSpeed);
            else
                driveZero(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR);

            if((driveTurnSpeed = gamepad1.right_stick_x) != 0)
                driveTurn(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, driveTurnSpeed);
            else
                driveZero(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR);
            //endregion

            //region Ring collecting
            if(gamepad2.left_stick_y != 0)
                collectRing(H1Motor2_Ramp0, H1Motor3_Ramp1, checkCollectSpeed(gamepad2, collectSpeed));
            else {
                H1Motor2_Ramp0.setPower(0);
                H1Motor3_Ramp1.setPower(0);
            }
            //endregion

            if(gamepad2.x)
                H2Servo1_Ring.setPosition(servoRingPosInnit);
            if(gamepad2.y)
                H2Servo1_Ring.setPosition(servoRingPosPush);

            //region Ring throwing
            if(gamepad2.right_stick_y != 0)
                H2Motor2_Throw.setPower(-0.65);
            else
                H2Motor2_Throw.setPower(0);
            //endregion

            //region Arm movement
            if(gamepad1.dpad_up || gamepad1.dpad_down)
                moveArm(H2Motor3_Arm, checkArmSpeed(gamepad1, armSpeed));
            else
                H2Motor3_Arm.setPower(0);
            //endregion

            //region Claw
            if(gamepad1.dpad_left || gamepad1.dpad_right)
                H2Servo0_Claw.setPosition(useClaw(H2Servo0_Claw, clawPos, gamepad1));
            //endregion

            //region Telemetry
            telemetry.addData("driveMoveSpeed", driveMoveSpeed);
            telemetry.addData("driveStrafeSpeed", driveStrafeSpeed);
            telemetry.addData("driveTurnSpeed", driveTurnSpeed);
            telemetry.addData("collectSpeed", collectSpeed);
            telemetry.addData("throwSpeed", throwSpeed);
            telemetry.addData("armSpeed", armSpeed);
            telemetry.addData("clawPos", clawPos);
            telemetry.update();
            //endregion
        }
    }
}
