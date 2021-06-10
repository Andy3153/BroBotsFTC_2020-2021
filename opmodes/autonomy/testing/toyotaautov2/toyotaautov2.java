package org.firstinspires.ftc.teamcode.opmodes.autonomy.testing.toyotaautov2;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import static com.sun.tools.doclint.HtmlTag.BR;
import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMaxPos;
import static org.firstinspires.ftc.teamcode.Functions.Constants.clawMinPos;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.autoDriveMove;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.autoDriveStrafe;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.autoDriveTurn;
import static org.firstinspires.ftc.teamcode.Functions.robotMovement.autoMoveArm;
import static org.firstinspires.ftc.teamcode.Functions.robotServos.useClaw;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Ultimate Goal game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "brongtest", group = "Autonomous")
public class toyotaautov2 extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";


    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "AfLjI7z/////AAABmfefgkFBzkBqjkZw7ZEnlgd0JVUNr2RNNV3HzOIE49up/XCm/ze7AQFfGSjcer9s17CL9/90gVg2/GCj3O8u/HK9hiKeferkokdnI1us9VnBZllh9fgSl6mg5ogK6QZy4n0nzPMudJX4Pv9hPicCK5yPx9nF7kKWMjIlQpa9iR3rehatCXq/0dkAsrHkBlrdxjCRtP/iPSouCqx13WlZ4zUYAjRZzyOOmFjGEtx8Mpe+UY0Lxq0E0/PqnfkBsqTLjKQD2njKgE4wMvzN8K4WtjKI6CGFBGW2oxlsLXEXZOka0Gh6UMagaQ/EqRi9/O26QboVmPLhNQxwZ8qCzftwgnH19FQG0/6XOQ4+YC2nNi3m";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        //region Declaring variables
        int collectSpeed = 0,
                throwSpeed = 0,
                armSpeed = 0;
        float clawPos = clawMinPos;
        double servoRingPosInnit = 0.92,
                servoRingPosPush = 0.68;
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
        Servo H2Servo1_Ring = hardwareMap.get(Servo.class, "H2Servo1_Ring");

        //endregion

        //region sa ma bata tata de stiu ce pula mea e asta si de ce imi trebuie
        H1Motor0_FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H2Motor0_FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H1Motor1_BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        H2Motor1_BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


//        H1Motor0_FL.setDirection(DcMotor.Direction.REVERSE);
//        H1Motor1_BL.setDirection(DcMotor.Direction.REVERSE);
        //endregion

        //region Setting Default Servo Positions
        H2Servo0_Claw.setPosition(clawMinPos);
        //endregion


        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();
        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(3.0, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        H2Servo1_Ring.setPosition(servoRingPosInnit);
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            if(recognition.getLabel().equals("Single")){
                                //Zona B, al doilea patrat, un rong
                                telemetry.addData("Un Brong-uri", "Zona B");
                                telemetry.update();
                                // 10 tick-uri = 70cm
                                autoDriveMove(H1Motor0_FL,H2Motor0_FR,H1Motor1_BL, H2Motor1_BR, 0.1, 35);//245 cm
                                autoMoveArm(H2Motor3_Arm, false, 0.2, 5);
                                H2Servo0_Claw.setPosition(clawMinPos);
                                autoMoveArm(H2Motor3_Arm, true, 0.2, 5);
                                autoDriveMove(H1Motor0_FL,H2Motor0_FR,H1Motor1_BL, H2Motor1_BR, 0.2, -35);
                                autoMoveArm(H2Motor3_Arm, false, 0.2, 5);
                                autoDriveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0.2, 1);// 7 cm
                                H2Servo0_Claw.setPosition(clawMaxPos);
                                autoDriveMove(H1Motor0_FL,H2Motor0_FR,H1Motor1_BL, H2Motor1_BR, 0.2, 20);
                                H2Servo1_Ring.setPosition(servoRingPosPush);
                                H2Servo1_Ring.setPosition(servoRingPosInnit);
                                autoDriveStrafe(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0.2, 5);//35 cm
                                autoDriveMove(H1Motor0_FL, H2Motor0_FR, H1Motor1_BL, H2Motor1_BR, 0.2,  10);//70 cm
                                H2Servo0_Claw.setPosition(clawMinPos);
                                sleep(100000);

                            }
                            else if(recognition.getLabel().equals("Quad")){
                                //Zona C, al treilea paterat, patru ronguri
                                telemetry.addData("Patru Brong-uri", "Zona C");
                                telemetry.update();
                                sleep(10000);
                            }
                        }
                        telemetry.update();
                    }
                    else{
                        //Zona A, primul patrat 0 ringuri==
                        telemetry.addData("Zero Bronguri", "Zona A");
                        telemetry.update();
                        sleep(10000);
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.5f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
