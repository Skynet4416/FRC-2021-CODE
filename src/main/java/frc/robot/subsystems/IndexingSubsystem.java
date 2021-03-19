package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SubsystemsFunctions;

/**
 * Chassis subsystem.
 */
public class IndexingSubsystem extends SubsystemBase {
    // Initialize motor controllers.
    private TalonSRX _spinner = new TalonSRX(Constants.Indexing.Motors.kSpinner);
    private TalonSRX _loader = new TalonSRX(Constants.Indexing.Motors.kLoader);
    /**
     * Creates the subsystem and configures motor controllers.
     */

    /**
     * Set indexing spin motor output.
     * 
     * @param spin  Spinner setpoint (percentage).
     */
    public void setSpinner(double spin) {
        if (SubsystemsFunctions.validPower(spin)) {
            this._spinner.set(ControlMode.PercentOutput, spin);
        }
    }

    /**
     * Set indexing load motor output.
     * 
     * @param load  Loader setpoint (percentage).
     */
    public void setLoader(double load) {
        if (SubsystemsFunctions.validPower(load)) {
            this._loader.set(ControlMode.PercentOutput, load);
        }
    }
}
