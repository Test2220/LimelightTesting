package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final double PIDSHOOTER_F = (1023 * 0.75) / 14500;
    public static final double PIDSHOOTER_P = (0.2 * 1023) / 1400;
    public static final double PIDSHOOTER_I = 0;
    public static final double PIDSHOOTER_D = PIDSHOOTER_P * 10;
    
}
