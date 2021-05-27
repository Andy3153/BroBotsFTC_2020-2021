package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;

public class robotServos {
    public static float useClaw(Servo claw, double clawPos, Gamepad gamepad)
    {
        if(gamepad.dpad_down)
            clawPos=clawPos>clawMaxPos-0.0225f?clawMaxPos:clawPos+0.0225f;
        else if(gamepad.dpad_up)
            clawPos=clawPos<clawMinPos+0.04f?clawMinPos:clawPos-0.4f;
        claw.setPosition(clawPos);

        return (float)clawPos;
    }
}