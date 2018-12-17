/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;

public class SetManipAngle extends Command {

  private int setposition;

  public SetManipAngle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.r_wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setposition = Robot.r_wrist.zeroAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.driverJoystick.getRawButton(OI.driverJoystickClockwiseButton)){
      // turn the wrist clockwise
      int posdelta = (int)((OI.driverJoystick.getRawAxis(OI.driverJoystickWristClockwiseAxis) + 1)/16 * 4096);
      setposition += posdelta;
      Robot.r_wrist.driveEncPos(setposition);

    } else if (OI.driverJoystick.getRawButton(OI.driverJoystickCounterClockwiseButton)){
      // turn the wrist counter clockwise
      int posdelta = (int)((OI.driverJoystick.getRawAxis(OI.driverJoystickWristCounterClockwiseAxis) + 1)/16 * 4096);
      setposition -= posdelta;
      Robot.r_wrist.driveEncPos(setposition);
    }
    SmartDashboard.putNumber("Manip Error", Robot.r_wrist.getPosError());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
    Robot.r_wrist.debugStopAll();
  }

}
