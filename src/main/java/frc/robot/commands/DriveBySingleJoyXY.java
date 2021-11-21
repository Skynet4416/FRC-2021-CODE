package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.subsystems.ChassisSubsystem;

/**
 * Command responsible for using 1 joysticks to drive the robot using X and Y.
 */
public class DriveBySingleJoyXY extends CommandBase {
    private ChassisSubsystem m_chassis;
    private DoubleSupplier m_X;
    private DoubleSupplier m_Y;

    /**
     * Creates a new DriveByJoy command.
     * 
     * @param chassis Chassis subsystem.
     * @param X    Supplier for the X input.
     * @param Y   Supplier for the Y input.
     */
    public DriveBySingleJoyXY(ChassisSubsystem chassis, DoubleSupplier X, DoubleSupplier Y) {
        this.addRequirements(chassis);
        this.m_chassis = chassis;
        this.m_X = X;
        this.m_Y = Y;
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
            y_val = Math.abs(x_val) > 0.1 ? x_val : 0;

            // Math.sin
    
            // this.m_chassis.set(left, right);
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
