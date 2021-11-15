package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Constants.Chassis;
import frc.robot.Meth_tools.Point;
import frc.robot.subsystems.ChassisSubsystem;

public class TestTurnToAngleWithNormalPID extends CommandBase {
    private double targetAngle;
    private ChassisSubsystem chassis;
    private AHRS ahrs;
    private double angle;
    private Point[] kB;
    private double kP;
    private NetworkTableEntry angleFromTargetEntry;
    private double ahrs_angle;
    private double power;
    private PIDController pid;
    public TestTurnToAngleWithNormalPID(ChassisSubsystem chassis,double angle, AHRS ahrs){
        this.ahrs = ahrs;
        this.angle = angle;
        this.targetAngle = this.angle - 90 + this.ahrs.getAngle(); // the target angle based on the real world( ahrs.getAngle() )
        this.chassis = chassis;
        this.ahrs_angle = ahrs.getAngle();
        this.pid = new PIDController(0.3, 0, 0);
    }
    @Override
    public void initialize()
    {
        // this.ahrs_angle = Math.abs(ahrs.getAngle()%360);
        this.ahrs_angle = ahrs.getAngle();
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable visionTable = inst.getTable("Vision");
        // this.angle = visionTable.getEntry("Angle From Target").getDouble(0);
        System.out.println("TABLE TARGET: " + angle + ", CONST TARGET: " + Constants.Chassis.testAngle);
        // A1 B0.75 C0.5 D0
        this.kB = new Point[] {new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointAx, 0),SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointAy, 0)),new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointBx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointBy, 0)), new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointCx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointCy, 0)), new Point(SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointDx, 0), SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAnglePointDy, 0))};
        this.targetAngle = angle - 90 + ahrs_angle; // the target angle based on the real world( ahrs.getAngle() )
        // this.targetAngle = Math.abs(this.targetAngle%360);
        this.kP = SmartDashboard.getNumber(Chassis.SmartDashboard.kP, Chassis.kP);
        pid.setP(kP);
        Globals.joysticksControlEnbaled = false;
        power = pid.calculate(this.ahrs_angle, this.targetAngle);
        chassis.set(-power, power);
    }
    @Override
    public boolean isFinished()
    {
        this.ahrs_angle = ahrs.getAngle();
        // System.out.println("ahrs angle: " + ahrs_angle + " angle got: " + angle + " target angle: " + this.targetAngle);
        power = pid.calculate(this.ahrs_angle, this.targetAngle);
        this.chassis.set(-power, power);
        System.out.println("LEFT - "+(0-power)+" RIGHT - "+(power));
        return Math.abs(this.targetAngle - this.ahrs.getAngle()) <= SmartDashboard.getNumber(Chassis.SmartDashboard.TurnAngleThreshold, 5);
    }
    @Override
    public void end(boolean interrupted){
        this.chassis.set(0, 0);
        Globals.joysticksControlEnbaled = true;

    }
}
