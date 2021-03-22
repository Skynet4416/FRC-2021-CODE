package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SubsystemsFunctions;

/**
 * Chassis subsystem.
 */
public class IntakeSubsystem extends SubsystemBase {
    // Initialize motor controllers.
    private VictorSPX _deploy = new VictorSPX(Constants.Intake.Motors.kDeploy);
    private VictorSPX _intake = new VictorSPX(Constants.Intake.Motors.kIntake);

    /**
     * Set deploy motor output.
     * 
     * @param power setpoint (percentage).
     */
    public void setDeploy(double power) {
        if (SubsystemsFunctions.validPower(power)) {
            this._deploy.set(ControlMode.PercentOutput, power);
        }
    }

    /**
     * Set intake motor output.
     * 
     * @param power setpoint (percentage).
     */
    public void setIntake(double power) {
        if (SubsystemsFunctions.validPower(power)) {
            this._intake.set(ControlMode.PercentOutput, power);
        }
    }
}
