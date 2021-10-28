package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;
import com.kauailabs.navx.frc.AHRS;

// DISCLAIMER: this doesn't have PID Controller, tho it needs it

public class TurnToAngle extends CommandBase{
    private double targetAngle;
    private ChassisSubsystem chassis;
    private AHRS ahrs;


    /**
        @param angle the target angle relative to the robot intial position(90 forward, 0 right etc)
    */

    public TurnToAngle(ChassisSubsystem chassis,double angle, AHRS ahrs){
        this.ahrs = ahrs;
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.chassis = chassis;
    }

    @Override
    public void initialize(){
        if(targetAngle > 90){ // left
            this.chassis.set(-Constants.Chassis.kTurnPowerPrecentage, Constants.Chassis.kTurnPowerPrecentage);
        } else { // right
            this.chassis.set(-Constants.Chassis.kTurnPowerPrecentage, Constants.Chassis.kTurnPowerPrecentage);
        }
    }

    @Override
    public boolean isFinished(){
        return Math.abs(this.targetAngle - this.ahrs.getAngle()) < Constants.Chassis.kThershold;
    }

    @Override
    public void end(boolean interrupted){
        this.chassis.set(0, 0);
    }
    
}
