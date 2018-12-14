/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class RotatingManipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX mc_wrist;
  
  public RotatingManipulator(){
    mc_wrist = new TalonSRX(RobotMap.mc_Manipulator_CANID);
    mc_wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, RobotMap.kMCTimeoutMS);
    mc_wrist.configContinuousCurrentLimit(20, RobotMap.kMCTimeoutMS);
    mc_wrist.configPeakCurrentDuration(200, RobotMap.kMCTimeoutMS);
    mc_wrist.configPeakCurrentLimit(60, RobotMap.kMCTimeoutMS);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double returnCurrentAngle(){
      // returns the current angle of the joint as read by an encoder.
      return 0.0;
  }

  
}
