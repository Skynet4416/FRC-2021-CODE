package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Shooter;

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
    }//peepee
}
