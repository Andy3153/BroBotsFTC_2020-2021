package org.firstinspires.ftc.teamcode.opmodes.autonomy.testing.toyotaauto;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// Our code *communism*

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkArmSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.autoDriveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.mm2cm;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.cm2in;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

import static org.firstinspires.ftc.teamcode.Functions.Constants.driveMotorTickCount;
import static org.firstinspires.ftc.teamcode.Functions.Constants.driveWheelCircumference;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.moveArm;

@Autonomous(name="toyotaauto", group="Autonomous")
public class toyotaauto extends LinearOpMode
{

    @Override
    public void runOpMode() // throws InterruptedException
    {
        //region Declaring variables
        int collectSpeed = 0,
                throwSpeed = 0,
                armSpeed = 0;
        float clawPos = clawMinPos;
        //endregion

        //region Declaring motors
        DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H2Motor0_FR = hardwareMap.get(DcMotor.class, "H2Motor0_FR");
        DcMotor H1Motor1_BL = hardwareMap.get(DcMotor.class, "H1Motor1_BL");
        DcMotor H2Motor1_BR = hardwareMap.get(DcMotor.class, "H2Motor1_BR");

        DcMotor H1Motor2_Ramp0 = hardwareMap.get(DcMotor.class, "H1Motor2_Ramp0");
        DcMotor H1Motor3_Ramp1 = hardwareMap.get(DcMotor.class, "H1Motor3_Ramp1");

        DcMotor H2Motor2_Throw = hardwareMap.get(DcMotor.class, "H2Motor2_Throw");

        DcMotor H2Motor3_Arm =hardwareMap.get(DcMotor.class, "H2Motor3_Arm");

        Servo H2Servo0_Claw = hardwareMap.get(Servo.class, "H2Servo0_Claw");

        //endregion

        //region sa ma bata tata de stiu ce pula mea e asta si de ce imi trebuie
        H1Motor0_FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H2Motor0_FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H1Motor1_BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H2Motor1_BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        H1Motor0_FL.setDirection(DcMotor.Direction.REVERSE);
//        H1Motor1_BL.setDirection(DcMotor.Direction.REVERSE);
        //endregion

        //region Setting Default Servo Positions
        H2Servo0_Claw.setPosition(clawMinPos);
        //endregion

        waitForStart();

        if(opModeIsActive()) {

            driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, -0.2f);
            sleep(200);
            driveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, -0.5f);
            sleep(700);
            driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0.2f);
            sleep(400);
            H1Motor0_FL.setPower(0);
            H1Motor1_BL.setPower(0);
            H2Motor0_FR.setPower(0);
            H2Motor1_BR.setPower(0);
            moveArm(H2Motor3_Arm, -0.7);
            sleep(1400);
            H2Servo0_Claw.setPosition(clawMaxPos);


        }
    }
}
