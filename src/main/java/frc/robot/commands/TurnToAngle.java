package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.subsystems.ChassisSubsystem;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.kauailabs.navx.frc.AHRS;
import frc.robot.Meth_tools.MethTools;
// DISCLAIMER: this doesn't have PID Controller, tho it needs it
import frc.robot.Meth_tools.Point;
import frc.robot.Constants.Chassis;

public class TurnToAngle extends CommandBase{
    private double targetAngle;
    private ChassisSubsystem chassis;
    private AHRS ahrs;
    private double angle;
    private Point[] kB;
    private double kP;

    /**
        @param angle the target angle relative to the robot intial position(90 forward, 0 right etc)
    */

    public TurnToAngle(ChassisSubsystem chassis,double angle, AHRS ahrs){
        this.ahrs = ahrs;
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.chassis = chassis;
        this.angle = angle;
        
    }

    @Override
    public void initialize(){
        // A1 B0.75 C0.5 D0
        this.kB = new Point[] {new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointAx, 0),SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointAy, 0)),new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointBx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointBy, 0)), new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointCx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointCy, 0)), new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointDx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointDy, 0))};
        this.targetAngle = angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.kP = SmartDashboard.getNumber(Chassis.SmartDashboard.kP, Chassis.kP);
        Globals.joysticksControlEnbaled = false;
        if(angle > 90){ // left
            this.chassis.set(-MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB), MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB));
        } else { // right
            this.chassis.set(MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB), -MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB));
        }
    }

    @Override
    public boolean isFinished(){
        if(angle > 90){ // left
            this.chassis.set(-MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB), MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB));
        } else { // right
            this.chassis.set(MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB), -MethTools.PController(this.targetAngle,this.ahrs.getAngle(),this.kP,Math.abs((angle - 90)),Constants.Chassis.kPmin,Constants.Chassis.kPmax,this.kB));
        }
        
        return Math.abs(this.targetAngle - this.ahrs.getAngle()) <= SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAngleThreshold, 5);
    }

    @Override
    public void end(boolean interrupted){
        this.chassis.set(0, 0);
        Globals.joysticksControlEnbaled = true;

    }
    
}
