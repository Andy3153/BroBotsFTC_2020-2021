package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.Gamepad;

public class    robotCheckSpeeds {
    public static int checkCollectSpeed(Gamepad gamepad, int speed){
        if (gamepad.left_stick_y > 0)
            speed = 1;
        else if (gamepad.left_stick_y < 0)
            speed = -1;
        else speed = 0;

        return speed;
    }

    public static int checkThrowSpeed(Gamepad gamepad, int speed){
        if(gamepad.right_stick_y > 0)
            speed = 1;
        else if(gamepad.right_stick_y < 0)
            speed = -1;
        else speed = 0;

        return speed;
    }

    public static double checkArmSpeed(Gamepad gamepad, double speed) {
        if (gamepad.dpad_up)
            speed = -0.7;
        else if (gamepad.dpad_down)
            speed = 0.7;
        else speed = 0;

        return speed;
    }
}
