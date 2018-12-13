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

  Joystick driverJoystick = new Joystick(1);

  // CAB:  FIXME WITH REAL BUTTON ASSIGMENTS
  Button alignRobotToSideWallButton = new JoystickButton(driverJoystick, 1);
  Button alignRobotToFrontWallButton = new JoystickButton(driverJoystick, 2);
  Button alignManipulatorToGameElementButton = new JoystickButton(driverJoystick, 3);
  Button turnAirlockGearButton = new JoystickButton(driverJoystick, 4);
  Button turnAirlockBarButton = new JoystickButton(driverJoystick, 5);

  // Select one of the below and uncomment depending on what controller is in use.

  // CAB:  FIXME WITH REAL NUMBERS
  // Logitech 3DPro
  // public static int driverJoystickXAxis = 1;
  // public static int driverJoystickYAxis = 2;
  // public static int driverJoystickTwistAxis = 3;

  // CAB:  FIXME WITH REAL NUMBERS
  // PS4 Gamepad
  public static int driverJoystickXAxis = 1;
  public static int driverJoystickYAxis = 2;
  public static int driverJoystickTwistAxis = 3;
}
