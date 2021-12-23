package frc.robot.subsystems;

import frc.robot.Constants.Shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Shooter PID subsystem.
 */
public class ShooterSubsystem extends SubsystemBase {
    // Motor controllers.
    private final CANSparkMax m_shooterMaster = new CANSparkMax(Shooter.kShooterMaster, MotorType.kBrushless);
    private final CANSparkMax m_shooterSlave = new CANSparkMax(Shooter.kShooterSlave, MotorType.kBrushless);
    private final CANPIDController m_pidController; // Master PID controller.
    private final CANEncoder m_encoder; // Master encoder.

    /**
     * Creates the subsystem, configures motor controllers, and sets PID constants.
     */
    public ShooterSubsystem() {
        // Restore default configuration of motor controllers.
        m_shooterMaster.restoreFactoryDefaults();
        m_shooterSlave.restoreFactoryDefaults();

        // Get master's PID controller & encoder.
        m_pidController = m_shooterMaster.getPIDController();
        m_encoder = m_shooterMaster.getEncoder();

        // Make slave follow with inversion.
        m_shooterSlave.follow(m_shooterMaster, true);

        // Set PID constants.
        m_pidController.setP(Shooter.PID.kP);
        m_pidController.setI(Shooter.PID.kI);
        m_pidController.setD(Shooter.PID.kD);
        m_pidController.setFF(Shooter.PID.kF);
        m_pidController.setIZone(Shooter.PID.kIZone);
        m_pidController.setOutputRange(0, 1);

        // Set 
        SmartDashboard.putNumber(Shooter.SmartDashboard.ShooterKP, Shooter.PID.kP);
        SmartDashboard.putNumber(Shooter.SmartDashboard.ShooterKI, Shooter.PID.kI);
        SmartDashboard.putNumber(Shooter.SmartDashboard.ShooterKD, Shooter.PID.kD);
        SmartDashboard.putNumber(Shooter.SmartDashboard.ShooterKF, Shooter.PID.kF);
    }

    @Override
    public void periodic() {
        m_pidController.setP(SmartDashboard.getNumber(Shooter.SmartDashboard.ShooterKP, Shooter.PID.kP));
        m_pidController.setI(SmartDashboard.getNumber(Shooter.SmartDashboard.ShooterKI, Shooter.PID.kI));
        m_pidController.setD(SmartDashboard.getNumber(Shooter.SmartDashboard.ShooterKD, Shooter.PID.kD));
        m_pidController.setFF(SmartDashboard.getNumber(Shooter.SmartDashboard.ShooterKF, Shooter.PID.kF));
        
        SmartDashboard.putNumber("Shooter Output", m_shooterMaster.getAppliedOutput());
        SmartDashboard.putNumber("Shooter Velocity (RPM)", m_encoder.getVelocity());
        SmartDashboard.putNumber("Shooter Current", m_shooterMaster.getOutputCurrent());

        SmartDashboard.putNumber("Shooter Slave Output", m_shooterSlave.getAppliedOutput());
    }

    /**
     * set power by rpm
     * @param rpm
     */
    public void setPID(double rpm) {
        if (rpm>0 && rpm <5500)
        {
        m_pidController.setIAccum(0);
        m_pidController.setReference(rpm, ControlType.kVelocity);
    }}
    /**
     * set power by speed
     * @param speed
     */
    public void setManual(double speed) {
        m_pidController.setReference(speed, ControlType.kDutyCycle);
    }
}
