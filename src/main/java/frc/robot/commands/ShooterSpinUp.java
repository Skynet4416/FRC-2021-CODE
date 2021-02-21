package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Command responsible for using 2 joysticks to drive the robot.
 */
public class ShooterSpinUp extends CommandBase {
    private ShooterSubsystem m_shooter;
    private DoubleSupplier m_supplier;

    /**
     * Creates a new DriveByJoy command.
     * 
     * @param shooter Shooter subsystem.
     * @param supplier    Supplier for motor output.
     */
    public DriveByJoy(ShooterSubsystem shooter, DoubleSupplier supplier)
        this.addRequirements(chassis);
        this.m_shooter = shooter;
        this.m_supplier = supplier;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        this.m_chassis.set(this.m_left.getAsDouble(), this.m_right.getAsDouble());
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
