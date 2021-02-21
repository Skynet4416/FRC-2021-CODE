package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private VictorSPX kRight = new VictorSPX(Constants.Climb.kRight);
    private VictorSPX kLeft = new VictorSPX(Constants.Climb.kLeft);
    DigitalInput kHallEffect = new DigitalInput(Constants.Climb.kHallEffect);
    
    /**
     * Set climb right motor output.
     * 
     * @param right Right setpoint (percentage).
     */
    public void setRight(double right) {
        if (Math.abs(right) > 1) {
            System.out.println("Climb: value for right engine isn't in range");
            return;
        }

        this.kRight.set(ControlMode.PercentOutput, right);
    }

    /**
     * Set climb left motor output.
     * 
     * @param left Left setpoint (percentage).
     */
    public void setLeft(double left) {
        if (Math.abs(left) > 1) {
            System.out.println("Climb: value for left engine isn't in range");
            return;
        }

        this.kLeft.set(ControlMode.PercentOutput, left);
    }
    /**
     * @return hall effect status
     */
    public boolean getHallEffectStatus(){
        return kHallEffect.get();
    }
}