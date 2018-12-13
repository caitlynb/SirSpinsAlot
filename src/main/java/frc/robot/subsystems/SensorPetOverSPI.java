/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SensorPetOverSPI extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private int spi_cs_pin;
  private double lastdistanceread;
  private SPI sensorpet;

  public SensorPetOverSPI(){
    this(0); // use the other init class with a default channel of 0
  }

  public SensorPetOverSPI(int spichannel){
    if(spichannel >= 0 && spichannel <= 3)
      this.spi_cs_pin = spichannel;
    else
      this.spi_cs_pin = 0;

    this.lastdistanceread = 0;

    switch(this.spi_cs_pin){
      case 1:
        sensorpet = new SPI(Port.kOnboardCS1);
        break;
      case 2:
        sensorpet = new SPI(Port.kOnboardCS2);
        break;
      case 3:
        sensorpet = new SPI(Port.kOnboardCS3);
        break;
      case 0:
      default:
        sensorpet = new SPI(Port.kOnboardCS0);
        break;
    }

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
