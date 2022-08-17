// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Distance To Target", getDistanceToTarget());
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

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

    //calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
}
}
