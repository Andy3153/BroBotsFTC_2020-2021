package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

public class robotServos {
    public static float useClaw(Servo claw, float clawPos, Gamepad gamepad)
    {
        if(gamepad.dpad_right)
            clawPos = clawMaxPos;
        else if(gamepad.dpad_left)
            clawPos = clawMinPos;
        claw.setPosition(clawPos);

        return clawPos;
    }
}