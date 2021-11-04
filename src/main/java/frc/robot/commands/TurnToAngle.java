package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.subsystems.ChassisSubsystem;
import com.kauailabs.navx.frc.AHRS;

// DISCLAIMER: this doesn't have PID Controller, tho it needs it

public class TurnToAngle extends CommandBase{
    private double targetAngle;
    private ChassisSubsystem chassis;
    private AHRS ahrs;
    private double angle;

    /**
        @param angle the target angle relative to the robot intial position(90 forward, 0 right etc)
    */

    public TurnToAngle(ChassisSubsystem chassis,double angle, AHRS ahrs){
        this.ahrs = ahrs;
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.chassis = chassis;
        this.angle = angle;
        
    }
    public double PController(){
        double currentAngle = Math.abs(this.targetAngle - this.ahrs.getAngle());
        double startDiff = Math.abs((angle - 90));
        return Math.min(0.2,Math.max(Constants.Chassis.kTurnPowerPrecentage *(currentAngle/startDiff),0.125));
    }
    @Override
    public void initialize(){
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )

        Globals.joysticksControlEnbaled = false;
        if(angle > 90){ // left
            this.chassis.set(-PController(), PController());
        } else { // right
            this.chassis.set(PController(), -PController());
        }
        
    }

    @Override
    public boolean isFinished(){
        if(angle > 90){ // left
            this.chassis.set(-PController(), PController());
        } else { // right
            this.chassis.set(PController(), -PController());
        }
        System.out.println(Math.abs(this.targetAngle - this.ahrs.getAngle()) + " / " + Math.abs((angle - 90)) + " = " + Math.abs(this.targetAngle - this.ahrs.getAngle()) / Math.abs((angle - 90)) + " , " + PController());
        return Math.abs(this.targetAngle - this.ahrs.getAngle()) < Constants.Chassis.kThershold;
    }

    @Override
    public void end(boolean interrupted){
        this.chassis.set(0, 0);
        Globals.joysticksControlEnbaled = true;

    }
    
}
