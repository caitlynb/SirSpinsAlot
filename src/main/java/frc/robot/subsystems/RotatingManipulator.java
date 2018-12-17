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
import frc.robot.Constants;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class RotatingManipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX mc_wrist;
  
  public RotatingManipulator(){
    mc_wrist = new TalonSRX(RobotMap.mc_Manipulator_CANID);

    mc_wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kMCTimeoutMS);
    mc_wrist.configContinuousCurrentLimit(20, Constants.kMCTimeoutMS);
    mc_wrist.configPeakCurrentDuration(200, Constants.kMCTimeoutMS);
    mc_wrist.configPeakCurrentLimit(60, Constants.kMCTimeoutMS);
    mc_wrist.enableCurrentLimit(true);

    mc_wrist.setSensorPhase(Constants.kWristSensorPhase);
    mc_wrist.selectProfileSlot(0, 0);
    mc_wrist.configNominalOutputForward(0, Constants.kMCTimeoutMS);
    mc_wrist.configNominalOutputReverse(0, Constants.kMCTimeoutMS);
    mc_wrist.configPeakOutputForward(1, Constants.kMCTimeoutMS);
    mc_wrist.configPeakOutputReverse(-1, Constants.kMCTimeoutMS);
    mc_wrist.config_kF(0, 0.0, Constants.kMCTimeoutMS);
    mc_wrist.config_kP(0, 0.2, Constants.kMCTimeoutMS);
    mc_wrist.config_kI(0, 0, Constants.kMCTimeoutMS);
    mc_wrist.config_kD(0, 0, Constants.kMCTimeoutMS);
  	mc_wrist.setNeutralMode(NeutralMode.Brake);
    mc_wrist.setInverted(false);
    
    mc_wrist.configAllowableClosedloopError(0, 5, Constants.kMCTimeoutMS);
    mc_wrist.configMotionAcceleration(3000, Constants.kMCTimeoutMS);
    mc_wrist.configMotionCruiseVelocity(6000, Constants.kMCTimeoutMS);

    zeroAngle();


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
      mc_wrist.set(ControlMode.MotionMagic, (int)(degrees / 360 * 4096));
    }
  }

  public void driveEncPos(int encpos){
    mc_wrist.set(ControlMode.MotionMagic, encpos);
  }

  public int zeroAngle(){
    // stealing demo code from
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/PositionClosedLoop/src/org/usfirst/frc/team217/robot/Robot.java
    int abspos = mc_wrist.getSensorCollection().getPulseWidthPosition();
    // mask out overflow bits
    abspos &= 0xFFF;
    if (Constants.kWristSensorPhase){
      abspos *= -1;
    }
    if (Constants.kWristInverted){
      abspos *= -1;
    }
    // set quad to match absolute
    mc_wrist.setSelectedSensorPosition(abspos, 0, Constants.kMCTimeoutMS);
    return abspos;
  }

  public void debugDrivePositive(){
    mc_wrist.set(ControlMode.PercentOutput, 0.25);
  }
  
  public void debugStopAll(){
    mc_wrist.set(ControlMode.PercentOutput, 0);
  }

  public int getPosError(){
    return mc_wrist.getClosedLoopError(0);
  }
}
