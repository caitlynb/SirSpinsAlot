/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Redo the numbers based on what they really are when you get home
  public static int mc_FrontLeft_CANID = 21;
  public static int mc_FrontRight_CANID = 20;
  public static int mc_RearLeft_CANID = 22;
  public static int mc_RearRight_CANID = 23;
  public static int mc_Manipulator_CANID = 10;

  // sp = Sensor Pet (the arduino with sensors attached)
  public static int sp_FrontLeft_SPIID = 0;
  public static int sp_FrontRight_SPIID = 1;
  public static int sp_SideFront_SPIID = 2;
  public static int sp_SideRear_SPIID = 3;

  // location of the Arduino for LEDs
  public static int arduino_I2CADDR = 8;



}
