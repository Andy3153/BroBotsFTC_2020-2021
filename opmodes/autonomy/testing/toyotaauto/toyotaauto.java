package org.firstinspires.ftc.teamcode.opmodes.autonomy.testing.toyotaauto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// Our code *communism*
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveTurn;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveZero;

import static org.firstinspires.ftc.teamcode.Functions.robotMovement.collectRing;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.moveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.throwRing;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkArmSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkThrowSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotCheckSpeeds.checkCollectSpeed;
import static org.firstinspires.ftc.teamcode.Functions.robotServos.useClaw;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

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
        float clawPos = clawMinPos,
                driveMoveSpeed,
                driveStrafeSpeed,
                driveTurnSpeed;
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
        //endregion

        //region Setting Default Servo Positions
        H2Servo0_Claw.setPosition(clawPos);
        //endregion

        waitForStart();


    }
}
