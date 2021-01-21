package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Chassis subsystem.
 */
public class ChassisSubsystem extends SubsystemBase {
    // Initialize motor controllers.
    private TalonSRX _rightMaster = new TalonSRX(Constants.Chassis.Motors.kMasterRight);
    private TalonSRX _rightSlave = new TalonSRX(Constants.Chassis.Motors.kSlaveRight);
    private TalonSRX _leftMaster = new TalonSRX(Constants.Chassis.Motors.kMasterLeft);
    private TalonSRX _leftSlave = new TalonSRX(Constants.Chassis.Motors.kSlaveLeft);

    /**
     * Creates the subsystem and configures motor controllers.
     */
    public ChassisSubsystem() {
        // Sets slaves to follow masters.
        this._rightSlave.follow(this._rightMaster);
        this._leftSlave.follow(this._leftMaster);
        this._rightSlave.setInverted(false);
        this._leftSlave.setInverted(false);
    }

    /**
     * Set chassis motor output.
     * 
     * @param left  Left setpoint (percentage).
     * @param right Right setpoint (percentage).
     */
    public void set(double left, double right) {
        if (Math.abs(left) > 1 || Math.abs(right) > 1) {
            // On invalid value, print error and return.
            System.out.println("Chassis: invalid value recieved to drive");
            return;
        }

        this._rightMaster.set(ControlMode.PercentOutput, right);
        this._leftMaster.set(ControlMode.PercentOutput, left);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Can be used for setting values in smart dashboard
    }
}
