/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.drive.MecanumDrive;   // crutch!

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBaseMecanum extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // private TalonSRX mc_front_left;
  // private TalonSRX mc_front_right;
  // private TalonSRX mc_rear_left;
  // private TalonSRX mc_rear_right;
  private WPI_TalonSRX mc_front_left;  // crutch
  private WPI_TalonSRX mc_front_right;
  private WPI_TalonSRX mc_rear_left;
  private WPI_TalonSRX mc_rear_right;


  private MecanumDrive drivebase;

  public DriveBaseMecanum(){
    mc_front_left = new WPI_TalonSRX(RobotMap.mc_FrontLeft_CANID);
    mc_front_right = new WPI_TalonSRX(RobotMap.mc_FrontRight_CANID);
    mc_rear_left = new WPI_TalonSRX(RobotMap.mc_RearLeft_CANID);
    mc_rear_right = new WPI_TalonSRX(RobotMap.mc_RearRight_CANID);

    mc_front_left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_front_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_rear_left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_rear_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    
    mc_front_left.configContinuousCurrentLimit(40, RobotMap.kMCTimeoutMS);
    mc_front_right.configContinuousCurrentLimit(40, RobotMap.kMCTimeoutMS);
    mc_rear_left.configContinuousCurrentLimit(40, RobotMap.kMCTimeoutMS);
    mc_rear_right.configContinuousCurrentLimit(40, RobotMap.kMCTimeoutMS);

    mc_front_left.configPeakCurrentDuration(200, RobotMap.kMCTimeoutMS);
    mc_front_left.configPeakCurrentLimit(60, RobotMap.kMCTimeoutMS);
    mc_front_right.configPeakCurrentDuration(200, RobotMap.kMCTimeoutMS);
    mc_front_right.configPeakCurrentLimit(60, RobotMap.kMCTimeoutMS);
    mc_rear_left.configPeakCurrentDuration(200, RobotMap.kMCTimeoutMS);
    mc_rear_left.configPeakCurrentLimit(60, RobotMap.kMCTimeoutMS);
    mc_rear_right.configPeakCurrentDuration(200, RobotMap.kMCTimeoutMS);
    mc_rear_right.configPeakCurrentLimit(60, RobotMap.kMCTimeoutMS);

    mc_front_left.enableCurrentLimit(true);
    mc_front_right.enableCurrentLimit(true);
    mc_rear_left.enableCurrentLimit(true);
    mc_rear_right.enableCurrentLimit(true);

    // MAJOR CRUTCH
    // TODO:  Put in some proper inverse kinematics driven by encoders!
    drivebase = new MecanumDrive(mc_front_left, mc_rear_left, mc_front_right, mc_rear_right);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void drivePolar(double magnitude, double angle, double zRotation){
    drivebase.drivePolar(magnitude, angle, zRotation);
  }

  public void driveCartesian(double xSpeed, double ySpeed, double zRotation){
    drivebase.driveCartesian(ySpeed, xSpeed, zRotation);
  }
}