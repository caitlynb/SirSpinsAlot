/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI r_oi;
  public static ArduinoLEDs r_arduino;
  public static DriveBaseMecanum r_drivebase;

  public static RotatingManipulator r_wrist;

  public static SensorPetOverSPI r_frontleftsensor;
  public static SensorPetOverSPI r_frontrightsensor;
  public static SensorPetOverSPI r_sideforwardsensor;
  public static SensorPetOverSPI r_siderearsensor;
  

  Command r_autonomousCommand;
  SendableChooser<Command> rd_autocommandhooser = new SendableChooser<>();
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
 
    // m_chooser.addDefault("Default Auto", new ExampleCommand());
    // m_chooser.addObject("My Auto", new MyAutoCommand());

    SmartDashboard.putData("Auto mode", rd_autocommandhooser);

    r_arduino = new ArduinoLEDs(RobotMap.arduino_I2CADDR);

    r_drivebase = new DriveBaseMecanum();

    r_wrist = new RotatingManipulator();

    r_frontleftsensor = new SensorPetOverSPI(RobotMap.sp_FrontLeft_SPIID);
    r_frontrightsensor = new SensorPetOverSPI(RobotMap.sp_FrontRight_SPIID);
    r_sideforwardsensor = new SensorPetOverSPI(RobotMap.sp_SideFront_SPIID);
    r_siderearsensor = new SensorPetOverSPI(RobotMap.sp_SideRear_SPIID);

    // learned the hard way, initialize OI last....
    r_oi = new OI();

    SmartDashboard.putNumber("Bar Turn Ticks", 2000);
    SmartDashboard.putNumber("Gear Turn Ticks", -1550);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    r_arduino.forceUpdate();
    SmartDashboard.putNumber("Current Reading Manipulator Position", r_wrist.getAngle());

    //SmartDashboard.putRaw("Raw Sensor Pet", r_frontleftsensor.debugmeasure());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    r_arduino.setModeDisabled();
    r_arduino.forceUpdate();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    r_autonomousCommand = rd_autocommandhooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    r_arduino.setModeAutonomous();
    r_arduino.forceUpdate();
    
    // schedule the autonomous command (example)
    if (r_autonomousCommand != null) {
      r_autonomousCommand.start();
    }


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //r_arduino.forceUpdate();
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (r_autonomousCommand != null) {
      r_autonomousCommand.cancel();
    }

    r_arduino.setModeTeleop();
    r_arduino.forceUpdate();

    SmartDashboard.putNumber("Set Manip Angle", 180);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();

    
  }

  /**
	 * This function is called on Test mode init
	 */
	@Override
	public void testInit(){
    r_arduino.setModeTest();
    r_arduino.forceUpdate();
  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


}
