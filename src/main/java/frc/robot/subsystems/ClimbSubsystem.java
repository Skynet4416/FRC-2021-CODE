package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private VictorSPX _right = new VictorSPX(Constants.Climb.kRight);
    private VictorSPX _left = new VictorSPX(Constants.Climb.kLeft);
    DigitalInput _hallEffect = new DigitalInput(Constants.Climb.DIO);
    
    /**
     * Set climb right motor output.
     * 
     * @param right Right setpoint (percentage).
     */
    public void setRight(double right) {
        if (Math.abs(right) > 1) {
            // On invalid value, print error and return.
            System.out.println("Climb: invalid value recieved to right engine");
            return;
        }

        this._right.set(ControlMode.PercentOutput, right);
    }

    /**
     * Set climb left motor output.
     * 
     * @param left Left setpoint (percentage).
     */
    public void setLeft(double left) {
        if (Math.abs(left) > 1) {
            // On invalid value, print error and return.
            System.out.println("Climb: invalid value recieved to left engine");
            return;
        }

        this._left.set(ControlMode.PercentOutput, left);
    }
    /**
     * @return hall effect status
     */
    public boolean getHallEffectStatus(){
        return _hallEffect.get();
    }
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Can be used for setting values in smart dashboard
    }
}