package org.firstinspires.ftc.teamcode.opmodes.testing.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveTurn;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.collectRing;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.moveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.throwRing;
import static org.firstinspires.ftc.teamcode.Functions.robotServos.useClaw;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

@TeleOp(name="test", group="TeleOp")
public class test extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Declaring variables
        int collectSpeed = 0,
            throwSpeed = 0,
            armSpeed = 0;
        float clawPos = 0; //clawMinPos;
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

        waitForStart();

        while(opModeIsActive())
        {
            //region Set default power for motors
            H1Motor0_FL.setPower(0);
            H2Motor0_FR.setPower(0);
            H1Motor1_BL.setPower(0);
            H2Motor1_BR.setPower(0);

            H1Motor2_Ramp0.setPower(0);
            H1Motor3_Ramp1.setPower(0);

            H2Motor2_Throw.setPower(0);
            H2Motor3_Arm.setPower(0);
            //endregion

            //region Driving
            if(gamepad1.left_stick_y != 0)
                driveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.left_stick_y);
            if(gamepad1.left_stick_x != 0)
                driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.left_stick_x);
            if(gamepad1.right_stick_x != 0)
                driveTurn(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.right_stick_x);
            //endregion

            //region Ring collecting
            if(gamepad2.left_stick_y != 0) {
                if (gamepad2.left_stick_y > 0)
                    collectSpeed = 1;
                else if (gamepad2.left_stick_y < 0)
                    collectSpeed = -1;
                else collectSpeed = 0;

                collectRing(H1Motor2_Ramp0, H1Motor3_Ramp1, collectSpeed);
            }
            //endregion

            //region Ring throwing
            if(gamepad2.right_stick_y != 0) {
                if (gamepad2.right_stick_y > 0)
                    throwSpeed = 1;
                else if (gamepad2.right_stick_y < 0)
                    throwSpeed = -1;
                else throwSpeed = 0;

                throwRing(H2Motor2_Throw, throwSpeed);
            }
            //endregion

            //region Arm movement
            if(gamepad2.dpad_up || gamepad2.dpad_down) {
                if (gamepad2.dpad_up)
                    armSpeed = 1;
                else if (gamepad2.dpad_down)
                    armSpeed = -1;
                else armSpeed = 0;

                moveArm(H2Motor3_Arm, armSpeed);
            }
            //endregion

            //region Claw
            if(gamepad1.dpad_left || gamepad1.dpad_right)
                useClaw(H2Servo0_Claw, clawPos, gamepad2);
            //endregion

            //region Telemetry
            telemetry.addData("clawPos: ", clawPos);
            telemetry.update();
            //endregion
        }
    }
}
