package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;

/**
 * Command responsible for using 1 joysticks to drive the robot using X and Y.
 */
public class DriveBySingleControlerXY extends CommandBase {
    private ChassisSubsystem m_chassis;
    private DoubleSupplier m_X;
    private DoubleSupplier m_Y;
    private DoubleSupplier m_limiter;

    /**
     * Creates a new DriveByJoy command.
     * 
     * @param chassis Chassis subsystem.
     * @param X    Supplier for the X input.
     * @param Y   Supplier for the Y input.
     * @param limiter Supplier for the throutle
     */
    public DriveBySingleControlerXY(ChassisSubsystem chassis, DoubleSupplier X, DoubleSupplier Y, DoubleSupplier limiter) {
        this.addRequirements(chassis);
        this.m_chassis = chassis;
        this.m_X = X;
        this.m_Y = Y;
        this.m_limiter = limiter;
    }

    public DriveBySingleControlerXY(ChassisSubsystem chassis, DoubleSupplier X, DoubleSupplier Y) {
        this.addRequirements(chassis);
        this.m_chassis = chassis;
        this.m_X = X;
        this.m_Y = Y;
        this.m_limiter = this::defaultLimiter;
    }

    private double defaultLimiter() {
        return -1;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Globals.joysticksControlEnbaled){
            double x_val = this.m_X.getAsDouble();
            x_val = Math.abs(x_val) > 0.1 ? x_val : 0;
            
            double y_val = this.m_Y.getAsDouble();
            y_val = Math.abs(y_val) > 0.1 ? y_val : 0;

            double r = Math.sqrt(x_val * x_val + y_val * y_val);
            double t = Math.atan2(y_val, x_val);

            double right = r * Math.cos(t - Math.toRadians(45));
            double left = r * Math.sin(t - Math.toRadians(45));
            
            // if forward or backward: make it really go forwars or backwards
            if(right * left > 0){
                right = (right + left) / 2;
                left = (right + left) / 2;
            }

            // used to limit the power output
            double limit = (1-this.m_limiter.getAsDouble()) / 2;
            right = limit * right;
            left = limit * left;

            this.m_chassis.set(left, right);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.m_chassis.set(0, 0);
    }
}
