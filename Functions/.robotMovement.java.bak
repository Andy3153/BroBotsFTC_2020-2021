package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotor;


public class robotMovement {

    public static void move(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(-speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(speed);
    }

    public static void strafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(-speed);
    }

    public static void turn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);
    }
}