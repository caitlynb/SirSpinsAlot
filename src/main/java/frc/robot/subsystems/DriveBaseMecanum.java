/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBaseMecanum extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX mc_front_left;
  private TalonSRX mc_front_right;
  private TalonSRX mc_rear_left;
  private TalonSRX mc_rear_right;

  public DriveBaseMecanum(){
    mc_front_left = new TalonSRX(RobotMap.mc_FrontLeft_CANID);
    mc_front_right = new TalonSRX(RobotMap.mc_FrontRight_CANID);
    mc_rear_left = new TalonSRX(RobotMap.mc_RearLeft_CANID);
    mc_rear_right = new TalonSRX(RobotMap.mc_RearRight_CANID);

    mc_front_left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_front_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_rear_left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    mc_rear_right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kMCTimeoutMS);
    

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}