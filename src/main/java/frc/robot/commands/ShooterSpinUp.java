package frc.robot.commands;

import java.util.function.DoubleSupplier;
// import java.util.function.Function;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Shooter;
// import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
// import jdk.nashorn.internal.ir.FunctionCall;

/**
 * Command responsible for ShooterSpinUp
 */
public class ShooterSpinUp extends CommandBase {
    private ShooterSubsystem m_shooter;
    private DoubleSupplier m_supplier;
    private NetworkTableEntry distanceFromTargetEntry;
    /**s
     * Creates a new DriveByJoy command.
     * 
     * @param shooter  Shooter subsystem.
     * @param supplier Supplier for motor output.
     */
    public ShooterSpinUp(ShooterSubsystem shooter, DoubleSupplier supplier) {
        this.addRequirements(shooter);
        this.m_shooter = shooter;
        this.m_supplier = supplier;
        }
    public ShooterSpinUp(ShooterSubsystem shooter)
    {
        this.m_shooter = shooter;   
        this.distanceFromTargetEntry = NetworkTableInstance.getDefault().getTable("Vision").getEntry("Distance From Target"); 
    }
    // public double calculateSpeed()
    // {
    //     this.targetSpeed = 200 * distanceFromTargetEntry.getDouble(0) + 3500;
    // }
    @Override
    public void initialize() {
        
        this.m_shooter.setPID(SmartDashboard.getNumber(Shooter.SmartDashboard.ShooterSetpoint, 4000));
        // this.m_shooter.setManual(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.m_shooter.setPID(0);
        // this.m_shooter.setManual(0);
    }
}
