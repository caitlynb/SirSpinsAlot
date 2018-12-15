/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.awt.Color;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ArduinoLEDs extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private int i2c_bus_address;
  private I2C arduino;
  private byte[] transmitbuffer;

  public enum ControlMode {
    Disabled,
    Autonomous,
    Teleop,
    Test
  }

  private ControlMode mode;

  public ArduinoLEDs(){
    this(8);
  }

  public ArduinoLEDs(int i2caddress){
    if(i2caddress >= 1 && i2caddress <= 255)
      this.i2c_bus_address = i2caddress;
    else
      this.i2c_bus_address = 8;

    arduino = new I2C(Port.kOnboard, i2c_bus_address);

    transmitbuffer = new byte[20];
    mode = ControlMode.Disabled;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setcolor(Color newcolor){
    transmitbuffer[2] = (byte)newcolor.getRed();
    transmitbuffer[3] = (byte)newcolor.getGreen();
    transmitbuffer[4] = (byte)newcolor.getBlue();
    arduino.writeBulk(transmitbuffer, 5);
  }

  public void setModeDisabled(){
    mode = ControlMode.Disabled;
    transmitbuffer[0] = 0x01;
  }

  public void setModeAutonomous(){
    mode = ControlMode.Autonomous;
    transmitbuffer[0] = 0x02;
  }

  public void setModeTest(){
    mode = ControlMode.Test;
    transmitbuffer[0] = 0x08;
  }

  public void setModeTeleop(){
    mode = ControlMode.Teleop;
    transmitbuffer[0] = 0x04;
  }

  public void forceUpdate(){
    arduino.writeBulk(transmitbuffer, 5);
  }
}
