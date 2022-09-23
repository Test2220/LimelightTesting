package frc.robot.subsystems.talon;

import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

@SuppressWarnings("unused")
public class MotorVelocity {
    WPI_TalonFX talon;

    private int kPIDLoopIdx = PIDconstants.kPIDLoopIdx;
    private int kTimeoutMs = PIDconstants.kTimeoutMs;

    private double kP = PIDconstants.kP;
    private double kI = PIDconstants.kI;
    private double kD = PIDconstants.kD;
    private double kF = PIDconstants.kF;

    private int kIzone = PIDconstants.kIzone;
    private double kPeakOutput = PIDconstants.kPeakOutput;

    public MotorVelocity(int canID) {}

    public MotorVelocity(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }



    private void build(int canID) {
        talon = new WPI_TalonFX(1, "rio");

        // Reset talon to defaults
        talon.configFactoryDefault();

        // TODO find out what this does
        talon.configNeutralDeadband(0.001);

        // Tell talon to use the integrated sensor for velocity
        talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                                            PIDconstants.kPIDLoopIdx, 
											PIDconstants.kTimeoutMs);
        
        // Configure max and min outputs
        talon.configNominalOutputForward(0, PIDconstants.kTimeoutMs);
		talon.configNominalOutputReverse(0, PIDconstants.kTimeoutMs);
		talon.configPeakOutputForward(1, PIDconstants.kTimeoutMs);
		talon.configPeakOutputReverse(-1, PIDconstants.kTimeoutMs);


        talon.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		talon.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		talon.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		talon.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    }
}
