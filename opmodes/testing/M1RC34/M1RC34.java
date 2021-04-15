package org.firstinspires.ftc.teamcode.opmodes.testing.M1RC34;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Functions.Constants;

import static org.firstinspires.ftc.teamcode.Functions.robotMovement.move;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.strafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.turn;

@TeleOp(name="M1RC34", group="TeleOp")
public class M1RC34 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        // Defining motors
        DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
        DcMotor H1Motor1_FR = hardwareMap.get(DcMotor.class, "H1Motor1_FR");
        //DcMotor H1Motor2_BL = hardwareMap.get(DcMotor.class, "H1Motor2_BL");
        //DcMotor H1Motor3_BR = hardwareMap.get(DcMotor.class, "H1Motor3_BR");

        // Setting default motor power
        H1Motor0_FL.setPower(0);
        H1Motor1_FR.setPower(0);
        //H1Motor2_BL.setPower(0);
        //H1Motor3_BR.setPower(0);

        waitForStart();

        boolean slow=false;
        while (opModeIsActive() && !gamepad1.x)
        {
            //region Speed & Init
            // Robot Slow Mode
            if(gamepad1.right_bumper) slow=!slow;
            float speedX=gamepad1.left_stick_x, speedY=gamepad1.left_stick_y, speedTurn=gamepad1.right_stick_x;
            speedX = slow?speedX*0.75f:speedX;
            speedY = slow?speedY*0.75f:speedY;
            speedTurn = slow?speedTurn*0.75f:speedTurn;

            H1Motor0_FL.setPower(0);
            H1Motor1_FR.setPower(0);
            //H1Motor2_BL.setPower(0);
            //H1Motor3_BR.setPower(0);
            //endregion

            //region Movement
            if(!gamepad1.a) {
                move(H1Motor0_FL, H1Motor1_FR, speedY);
                strafe(H1Motor0_FL, H1Motor1_FR, speedX);
                turn(H1Motor0_FL, H1Motor1_FR, speedTurn);
            }
            else {
                move(H1Motor0_FL, H1Motor1_FR, 0);
                strafe(H1Motor0_FL, H1Motor1_FR, 0);
                turn(H1Motor0_FL, H1Motor1_FR, 0);
            }
            //endregion
        }
    }
}
