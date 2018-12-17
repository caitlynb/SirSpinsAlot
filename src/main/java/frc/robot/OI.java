/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//import javax.print.attribute.standard.JobPriority;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static Joystick driverJoystick = new Joystick(0);

  // CAB:  FIXME WITH REAL BUTTON ASSIGMENTS
  public static Button alignRobotToSideWallButton = new JoystickButton(driverJoystick, 3);
  public static Button alignRobotToFrontWallButton = new JoystickButton(driverJoystick, 4);
  public static Button alignManipulatorToGameElementButton = new JoystickButton(driverJoystick, 10);
  public static Button turnAirlockGearButton = new JoystickButton(driverJoystick, 1);
  public static Button turnAirlockBarButton = new JoystickButton(driverJoystick, 2);
  public static Button toggleHeadlights = new JoystickButton(driverJoystick, 13);
  public static Button controlManipulatorFromDashboard = new JoystickButton(driverJoystick, 9);

  // Select one of the below and uncomment depending on what controller is in use.

  // CAB:  FIXME WITH REAL NUMBERS
  // Logitech 3DPro
  // public static int driverJoystickXAxis = 1;
  // public static int driverJoystickYAxis = 2;
  // public static int driverJoystickTwistAxis = 3;

  // CAB:  FIXME WITH REAL NUMBERS
  // PS4 Gamepad
  public static int driverJoystickXAxis = 1;  // In NED convention, this is front positive, back negative
  public static int driverJoystickYAxis = 0;  // in NED convention, this is right positive, left negative
  public static int driverJoystickTwistAxis = 2;
  public static int driverJoystickWristClockwiseAxis = 4;
  public static int driverJoystickClockwiseButton = 8;
  public static int driverJoystickWristCounterClockwiseAxis = 3;
  public static int driverJoystickCounterClockwiseButton = 7;

  // Set up some commands
  public OI(){
    toggleHeadlights.toggleWhenPressed(new TurnOnHeadlights());
    controlManipulatorFromDashboard.whileHeld(new SetManipAngle());
    alignRobotToSideWallButton.whileHeld(new DriveOrientedToSideWall());
    alignRobotToFrontWallButton.whileHeld(new DriveOrientedToFront());
    turnAirlockBarButton.whenPressed(new OpenBar());
    turnAirlockGearButton.whenPressed(new OpenGear());
    // alignRobotToSideWallButton.whileHeld(new debugDriveBaseForward());
    // alignRobotToFrontWallButton.whileHeld(new debugWristForward());
  }
}
