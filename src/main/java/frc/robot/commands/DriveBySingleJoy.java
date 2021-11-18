package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.subsystems.ChassisSubsystem;

/**
 * Command responsible for using 1 joysticks to drive the robot.
 */
public class DriveBySingleJoy extends CommandBase {
    private ChassisSubsystem m_chassis;
    private DoubleSupplier m_axis;
    private DoubleSupplier m_rotation;

    /**
     * Creates a new DriveBySingleJoy command.
     * 
     * @param chassis Chassis subsystem.
     * @param left    Supplier for the motors output.
     * @param rotation   Supplier for rotation.
     */
    public DriveBySingleJoy(ChassisSubsystem chassis, DoubleSupplier axis, DoubleSupplier rotation) {
        this.addRequirements(chassis);
        this.m_chassis = chassis;
        this.m_axis = axis;
        this.m_rotation = rotation;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Globals.joysticksControlEnbaled){
            double amount_axis = this.m_axis.getAsDouble();
            double left = Math.abs(amount_axis) > 0.1 ? amount_axis : 0;
            double right = Math.abs(amount_axis) > 0.1 ? amount_axis : 0;
            
            double amount_rotation = this.m_rotation.getAsDouble();
            amount_rotation = Math.abs(amount_rotation) > 0.5 ? amount_rotation : 0;
            
            if(amount_rotation < 0) 
                left = -left;
            else if(amount_rotation > 0)
                right = -right;
            
    
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
