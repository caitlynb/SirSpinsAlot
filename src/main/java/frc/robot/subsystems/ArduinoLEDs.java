/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ArduinoLEDs extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private int i2c_bus_address;

  public ArduinoLEDs(){
    this(7);
  }

  public ArduinoLEDs(int i2caddress){
    if(i2caddress >= 1 && i2caddress <= 255)
      this.i2c_bus_address = i2caddress;
    else
      this.i2c_bus_address = 7;


  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
