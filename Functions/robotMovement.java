package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotor;

public class robotMovement {
    public static void driveMove(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(-speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(speed);
    }

    public static void driveStrafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(-speed);
    }

    public static void driveTurn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);
    }

    public static void collectRing(DcMotor R0, DcMotor R1, int speed) {
        R0.setPower(speed);
        R1.setPower(speed);
    }

    public static void throwRing(DcMotor T0, int speed) {
        T0.setPower(speed);
    }

    public static void moveArm(DcMotor A0, int speed) {
        A0.setPower(speed);
    }
}