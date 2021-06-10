package org.firstinspires.ftc.teamcode.opmodes.teleop.testing.M1RC34;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// Our code *communism*
import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.driveTurn;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.collectRing;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.moveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotServos.useClaw;

@TeleOp(name="M1RC34", group="TeleOp")
@Disabled
public class M1RC34 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        //region Declaring variables
        int collectSpeed = 0,
            throwSpeed = 0,
            armSpeed = 0;
        float clawPos=clawMinPos,
              speedX = gamepad1.left_stick_x,
              speedY = gamepad1.left_stick_y,
              speedTurn = gamepad1.right_stick_x;
        //endregion

        //region Defining motors
        //Driving Motors
          DcMotor H1Motor0_FL = hardwareMap.get(DcMotor.class, "H1Motor0_FL");
          DcMotor H2Motor0_FR = hardwareMap.get(DcMotor.class, "H2Motor0_FR");
          DcMotor H1Motor1_BL = hardwareMap.get(DcMotor.class, "H1Motor1_BL");
          DcMotor H2Motor1_BR = hardwareMap.get(DcMotor.class, "H2Motor1_BR");

        //Collecting System Motors
          DcMotor H1Motor2_Ramp0 = hardwareMap.get(DcMotor.class, "H1Motor2_Ramp0");
          DcMotor H1Motor3_Ramp1 = hardwareMap.get(DcMotor.class, "H1Motor3_Ramp1");

        //Throwing System Motor
          DcMotor H2Motor2_Throw = hardwareMap.get(DcMotor.class, "H2Motor2_Throw");

        //Wobble Goal Motor
          DcMotor H2Motor3_Arm = hardwareMap.get(DcMotor.class, "H2Motor3_Arm");

        //Wobble Goal Claw Servo
          Servo H2Servo0_Claw = hardwareMap.get(Servo.class, "H2Servo0_Claw");
        //endregion

        waitForStart();

//        boolean slow=false;
        while (opModeIsActive()) // && !gamepad1.x)
        {

            //region Setting Motor Power
            //Driving Motors
            H1Motor0_FL.setPower(0);
            H2Motor0_FR.setPower(0);
            H1Motor1_BL.setPower(0);
            H2Motor1_BR.setPower(0);

            //Collecting System Motors
            H1Motor2_Ramp0.setPower(0);
            H1Motor3_Ramp1.setPower(0);

            //Throwing System Motor
            H2Motor2_Throw.setPower(0);

            //Wobble Goal Motor
            H2Motor3_Arm.setPower(0);
            //endregion

            //region Robot Slow Mode
//            if(gamepad1.right_bumper) slow=!slow;
//            float speedX=gamepad1.left_stick_x, speedY=gamepad1.left_stick_y, speedTurn=gamepad1.right_stick_x;
//            speedX = slow?speedX*0.75f:speedX;
//            speedY = slow?speedY*0.75f:speedY;
//            speedTurn = slow?speedTurn*0.75f:speedTurn;
            //endregion

            //region Driving
//            if(!gamepad1.a) {
                driveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.left_stick_y);
                driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.left_stick_x);
                driveTurn(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, gamepad1.right_stick_x);
//            }
//            else {
//                driveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0);
//                driveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0);
//                driveTurn(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0);
//            }
            //endregion

            //region Collecting System
            if(gamepad2.left_stick_y > 0)
                collectSpeed = 1;
            else if(gamepad2.left_stick_y < 0)
                collectSpeed = -1;
            else collectSpeed = 0;

            collectRing(H1Motor2_Ramp0, H1Motor3_Ramp1, collectSpeed);
            //endregion

            //region Throwing System
            if(gamepad2.right_stick_y > 0)
                throwSpeed = 1;
            else if(gamepad2.right_stick_y < 0)
                throwSpeed = -1;
            else throwSpeed = 0;

            //endregion

            //region Arm System
            if(gamepad2.dpad_up)
                armSpeed = 1;
            else if(gamepad2.dpad_down)
                armSpeed = -1;
            else armSpeed = 0;

            moveArm(H2Motor3_Arm, armSpeed);
            //endregion

            //region Claw System
            useClaw(H2Servo0_Claw, clawPos, gamepad2);
            //endregion
        }
    }
}
