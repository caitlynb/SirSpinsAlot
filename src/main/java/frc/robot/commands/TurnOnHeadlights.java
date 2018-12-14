/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.awt.Color;

public class TurnOnHeadlights extends Command {
  public TurnOnHeadlights() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.r_arduino);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // put code here to toggle on headlights
    Robot.r_arduino.setcolor(Color.WHITE);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // force the code to never assume 'headlights' are finished
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
    // put code here to toggle off headlights
    Robot.r_arduino.setcolor(Color.black);
  }
}
