package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Chassis subsystem.
 */
public class IntakeSubsystem extends SubsystemBase {
    // Initialize motor controllers.
    private TalonSRX _deploy = new TalonSRX(Constants.Intake.Motors.kDeploy);
    private VictorSPX _intake = new VictorSPX(Constants.Intake.Motors.kIntake);

    /**
     * Creates the subsystem and configures motor controllers.
     */
    public IntakeSubsystem() {
        // _deploy.setInverted(InvertType.InvertMotorOutput);
        // _intake.setInverted(InvertType.InvertMotorOutput);
    }

    /**
     * Set deploy motor output.
     * 
     * @param power setpoint (percentage).
     */
    public void setDeploy(double power) {
        if (Math.abs(power) > 1) {
            // On invalid value, print error and return.
            System.out.println("Intake (deploy): invalid value recieved to drive");
            return;
        }
        this._deploy.set(ControlMode.PercentOutput, power);
    }

    /**
     * Set intake motor output.
     * 
     * @param power setpoint (percentage).
     */
    public void setIntake(double power) {
        if (Math.abs(power) > 1) {
            // On invalid value, print error and return.
            System.out.println("Intake: invalid value recieved to drive");
            return;
        }
        this._intake.set(ControlMode.PercentOutput, power);
    }

    /**
     * Get deploy motor's drawn current.
     */
    public double getDeployCurrent() {
        return this._deploy.getStatorCurrent();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Can be used for setting values in smart dashboard
    }
}
