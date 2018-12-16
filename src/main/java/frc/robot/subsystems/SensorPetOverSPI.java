/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/* Documenation for wiring for sensor pets:
 * 
 * MOSI:  White
 * MISO:  Yellow
 * SCK:   Orange
 * CS0:   Grey
 * CS1:   Green
 * CS2:   Blue
 * CS3:   Purple
 **/

package frc.robot.subsystems;

import java.nio.ByteBuffer;

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
  private byte[] writebuffer;
  private byte[] receiverbuffer;
  private DistanceMode mode;
  private MeasurementSystem units;

  public enum DistanceMode {
    ULTRASONIC,
    LIDAR
  }

  public enum MeasurementSystem {
    MM,
    CM,
    M,
    FT,
    IN
  }

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

    sensorpet.setClockRate(2000000); // 2 MHz
    sensorpet.setClockActiveHigh();
    sensorpet.setChipSelectActiveLow();
    sensorpet.setSampleDataOnRising();
    sensorpet.setMSBFirst();

    writebuffer = new byte[10];
    receiverbuffer = new byte[10];

    units = MeasurementSystem.MM;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /*
  public void configUltrasonic(){
    this.chooseSensor(DistanceMode.ULTRASONIC);
  }

  public void configLIDAR(){
    this.chooseSensor(DistanceMode.LIDAR);
  }

  public String chooseSensor(DistanceMode newMode){
    mode = newMode;
    switch(newMode){
      case ULTRASONIC:
        writebuffer[0] = 4;
        break;
      case LIDAR:
        writebuffer[0] = 8;
        break;
      default:
        writebuffer[0] = 0;
    }
    sensorpet.write(writebuffer, 1);
    return mode.toString();
  }
*/

  public void setMeasurementSystem(MeasurementSystem meas){
    units = meas;
  }

  public double measure(){
    sensorpet.read(true, receiverbuffer, 6);  // returns millimeters as an int
    double mm = ByteBuffer.wrap(receiverbuffer).getInt();
    switch(units){
      case MM:
        lastdistanceread = mm;
        break;
      case CM:
        lastdistanceread = mm / 10;
        break;
      case M:
        lastdistanceread = mm / 1000;
        break;
      case FT:
        lastdistanceread = mm / 304.8;
        break;
      case IN:
        lastdistanceread = mm / 25.4;
        break;
    }
    return lastdistanceread;
  }

  public byte[] debugmeasure(){
    sensorpet.read(true, receiverbuffer, 6);
    return receiverbuffer;
  }

  public double lastmeasurement(){
    return lastdistanceread;
  }

}
