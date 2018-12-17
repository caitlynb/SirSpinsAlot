/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Constants;

public class OpenBar extends Command {

  private int setposition;
  private int targetposition;

  public OpenBar() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.r_wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setposition = Robot.r_wrist.zeroAngle();
    int delta = (int)SmartDashboard.getNumber("Bar Turn Ticks", 2000);
    targetposition = setposition + delta;
    Robot.r_wrist.driveEncPos(targetposition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    int curpos = Robot.r_wrist.getAngle();
    if (Math.abs(curpos - targetposition) < Constants.kAllowedAnglePosError){
      return true;
    } else 
      return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
