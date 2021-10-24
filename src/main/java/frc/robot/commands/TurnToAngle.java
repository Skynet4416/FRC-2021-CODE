package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Chassis;
import frc.robot.subsystems.ChassisSubsystem;

import com.kauailabs.navx.frc.AHRS;

// DISCLAIMER: this doesn't have PID Controller, tho it needs it

public class TurnToAngle extends CommandBase{
    private final double EPSILON = 10;
    private double F = 0.2;
    private double targetAngle;
    private ChassisSubsystem chassis;
    private AHRS ahrs;


    /**
        @param angle the target angle relative to the robot intial position(90 forward, 0 right etc)
    */

    public TurnToAngle(double angle, AHRS ahrs, ChassisSubsystem chassis){
        this.ahrs = ahrs;
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.chassis = chassis;
    }

    @Override
    public void initialize(){
        if(targetAngle > 90){ // left
            this.chassis.set(-F, F);
        } else { // right
            this.chassis.set(-F, F);
        }
    }

    @Override
    public boolean isFinished(){
        return Math.abs(this.targetAngle - this.ahrs.getAngle()) < EPSILON;
    }

    @Override
    public void end(boolean interrupted){
        this.chassis.set(0, 0);
    }
    
}
