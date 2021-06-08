package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.DcMotor;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Functions.Constants.driveMotorTickCount;
import static org.firstinspires.ftc.teamcode.Functions.Constants.driveWheelCircumference;

public class robotMovement {
    public static void driveMove(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(-speed);
        FR.setPower(speed);
        BL.setPower(-speed);
        BR.setPower(speed);
    }

    public static void driveStrafe(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(-speed);
        FR.setPower(-speed);
        BL.setPower(speed);
        BR.setPower(speed);
    }

    public static void driveTurn(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, float speed) {
        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);
    }

    public static void driveZero(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR) {
        FL.setPower(0);
        FR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }

    public static void collectRing(DcMotor R0, DcMotor R1, int speed) {
        R0.setPower(speed);
        R1.setPower(speed);
    }

    public static void throwRing(DcMotor T0, int speed) {
        T0.setPower(speed);
    }

    public static void moveArm(DcMotor A0, double speed) {
        A0.setPower(speed);
    }

    //Autonomy shit
    public static void autoDriveMove(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, double speed, double howManyCm)
    {
        int MOTOR_TICK_COUNTS = 538;

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double circumference = 3.14 * 3.7795275590551185;


    }



    public static double mm2cm(double mm) { double cm = mm / 10 ; return cm; }
    public static double cm2in(double cm) { double in = cm / 2.54 ; return in; }








    /*
    public static void autoDriveMove(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, double speed, double howManyCm) //int encoderDrivingTarget) //int howManyCm)
    {
        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double rotationsNeeded = howManyCm / driveWheelCircumference;
        int encoderDrivingTarget = (int)(rotationsNeeded * driveMotorTickCount);

        FL.setTargetPosition(encoderDrivingTarget);
        FR.setTargetPosition(encoderDrivingTarget);
        BL.setTargetPosition(encoderDrivingTarget);
        BR.setTargetPosition(encoderDrivingTarget);

        FL.setPower(speed);
        FR.setPower(speed);
        BL.setPower(speed);
        BR.setPower(speed);

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy()) {
//            telemetry.addData("Status", "Driving", howManyCm, "centimeters");
//            telemetry.update();
            int sugipula=69420;
            int bagpulainrobotica = 0;
            sugipula = sugipula + bagpulainrobotica;
        }

        FL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
        BL.setPower(0);

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void autoMoveArm(DcMotor motor, boolean reverse, double power, int ticks) {
        if (reverse)
            motor.setDirection(DcMotor.Direction.REVERSE);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setTargetPosition(ticks);
        motor.setPower(power);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motor.isBusy()) {
//            telemetry.addData("Status", "Misc. Motor running");
//            telemetry.update();
        }

        motor.setPower(0);
    }

     */
}