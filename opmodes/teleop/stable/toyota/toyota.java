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
        DcMotor H1Motor0_FR = hardwareMap.get(DcMotor.class, "H1Motor0_FR");
        DcMotor H1Motor1_BL = hardwareMap.get(DcMotor.class, "H1Motor1_BL");
        DcMotor H1Motor1_BR = hardwareMap.get(DcMotor.class, "H1Motor1_BR");
        //endregion

        //region Setting Default Servo Position
        //endregion

        waitForStart();

        while(opModeIsActive())
        {
            //region Driving
            if(gamepad1.x){
                H1Motor0_FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                H1Motor1_BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                H1Motor0_FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                H1Motor1_BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

                telemetry.addData("Pozitite FL", H1Motor0_FL.getCurrentPosition());
                telemetry.addData("Pozitite FR", H1Motor0_FR.getCurrentPosition());
                telemetry.addData("Pozitite BL", H1Motor1_BL.getCurrentPosition());
                telemetry.addData("Pozitite BR", H1Motor1_BR.getCurrentPosition());
                telemetry.update();


            //region Telemetry
//            telemetry.addData("driveMoveSpeed", driveMoveSpeed);
//            telemetry.addData("driveStrafeSpeed", driveStrafeSpeed);
//            telemetry.addData("driveTurnSpeed", driveTurnSpeed);
//            telemetry.addData("collectSpeed", collectSpeed);
//            telemetry.addData("throwSpeed", throwSpeed);
//            telemetry.addData("armSpeed", armSpeed);
//            telemetry.addData("clawPos", clawPos);
//            telemetry.update();
            //endregion
        }
    }
}
