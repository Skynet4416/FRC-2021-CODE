package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
    public IndexingSubsystem() {
        // Sets slaves to follow masters.
        // this._rightSlave.follow(this._rightMaster);
        // this._leftSlave.follow(this._leftMaster);
        // this._rightSlave.setInverted(false);
        // this._leftSlave.setInverted(false);
    }

    /**
     * Set indexing spin motor output.
     * 
     * @param spin  Spinner setpoint (percentage).
     */
    public void setSpinner(double spin) {
        if (Math.abs(spin) > 1) {
            // On invalid value, print error and return.
            System.out.println("Indexing: invalid value recieved to spin");
            return;
        }
        this._spinner.set(ControlMode.PercentOutput, spin);
    }

    /**
     * Set indexing load motor output.
     * 
     * @param load  Loader setpoint (percentage).
     */
    public void setLoader(double load) {
        if (Math.abs(load) > 1) {
            // On invalid value, print error and return.
            System.out.println("Indexing: invalid value recieved to load");
            return;
        }
        this._spinner.set(ControlMode.PercentOutput, load);
    }
    

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Can be used for setting values in smart dashboard
    }
}
