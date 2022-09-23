// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.TreeMap;
import java.util.Map.Entry;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Distance To Target", getDistanceToTarget());
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }

  private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-shooter");
  /* SUBSYSTEM CONSTRUCTOR */

  /**
   * Limelight subsystem constructor. No arguments necessary or present.
   */
  // Set camera mode to use vision processing

  public double getVOffset() {
    NetworkTableEntry targetY = limelight.getEntry("ty");
    double val = targetY.getDouble(0);
    return val;
  }

  public double getDistanceToTarget() {
    NetworkTableEntry ty = limelight.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    SmartDashboard.putNumber("targetOffsetAngleVertical", targetOffsetAngle_Vertical);
    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 0;

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 30.65;

    // distance from the target to the floor
    double goalHeightInches = 38.5;

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    SmartDashboard.putNumber("angleToGoalDegrees", angleToGoalDegrees);
    double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);

    // calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
        / Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }

  // public double flywheelRPM(){
  // double distance = getDistanceToTarget();
  // if (distance == 12) {

  // }
  // }
  public double test() {
    TreeMap<Double, Double> map = new TreeMap<>();
    map.put(0.0, 50.0);
    map.put(24.0, 100.0);
    map.put(48.0, 150.0);
    map.put(72.0, 200.0);
    map.put(96.0, 250.0);
    map.put(120.0, 300.0);
    double distance = 12;

    Double speed = map.get(distance);
    if (speed == null) {
      Entry<Double, Double> c = map.ceilingEntry(distance);
      Entry<Double, Double> f = map.floorEntry(distance);
      if (c == null) {
        if (f == null) {
          return 0;
        } else {
          return f.getValue();
        }
      } else {
        if (f == null) {
          return c.getValue();
        } else {
          double valuef = f.getValue();
          double valuec = c.getValue();
          double keyf = f.getKey();
          double keyc = c.getKey();
          return speed.doubleValue();
        }
      }
    } else {
     return speed.doubleValue();
    }
  }
}
