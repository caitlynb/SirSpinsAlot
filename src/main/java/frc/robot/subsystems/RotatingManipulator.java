/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;


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

    mc_wrist.setSensorPhase(true);
    mc_wrist.selectProfileSlot(0, 0);
    mc_wrist.configNominalOutputForward(0, 10);
    mc_wrist.configNominalOutputReverse(0, 10);
    mc_wrist.configPeakOutputForward(1, 10);
    mc_wrist.configPeakOutputReverse(-1, 10);
    mc_wrist.config_kF(0, 0.4, 10);
    mc_wrist.config_kP(0, 0.3, 10);
    mc_wrist.config_kI(0, 0, 10);
    mc_wrist.config_kD(0, 0, 10);
  	mc_wrist.setNeutralMode(NeutralMode.Coast);
    mc_wrist.setInverted(false);
    
    mc_wrist.configMotionAcceleration(3000, RobotMap.kMCTimeoutMS);
    mc_wrist.configMotionCruiseVelocity(10000, RobotMap.kMCTimeoutMS);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public int getAngle(){
    // returns the current angle of the joint as read by an encoder.
    int angle = mc_wrist.getSensorCollection().getPulseWidthPosition();
    return angle;
  }

  public void setAngle(double degrees){
    // sets the current angle
    if(degrees>=0 && degrees <= 360){
      mc_wrist.set(ControlMode.Position, degrees * 4096);
    }
  }

  public void debugDrivePositive(){
    mc_wrist.set(ControlMode.PercentOutput, 0.25);
  }
  
  public void debugStopAll(){
    mc_wrist.set(ControlMode.PercentOutput, 0);
  }
}
