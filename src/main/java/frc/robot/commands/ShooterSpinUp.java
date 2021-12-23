package frc.robot.commands;

import java.util.function.DoubleSupplier;
// import java.util.function.Function;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Shooter;
import frc.robot.Meth_tools.MethTools;
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
        this.distanceFromTargetEntry = NetworkTableInstance.getDefault().getTable("Vision").getEntry("Distance From Target"); 
        }
    public ShooterSpinUp(ShooterSubsystem shooter)
    {
        this.m_shooter = shooter;   
        this.distanceFromTargetEntry = NetworkTableInstance.getDefault().getTable("Vision").getEntry("Distance From Target"); 
    }
    public double calculateSpeed()
    {
        // return MethTools.GetShooterSpeed(distanceFromTargetEntry.getDouble(0));
        return 333.33 * distanceFromTargetEntry.getDouble(0) + 3650;
    }
    @Override
    public void initialize() {
        System.out.println("hi");
        // this.m_shooter.setPID(SmartDashboard.getNumber(Constants.Shooter.SmartDashboard.ShooterSetpoint, 0));
        this.m_shooter.setPID(calculateSpeed());
        // this.m_shooter.setManual(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // this.m_shooter.setPID(0);
        this.m_shooter.setManual(0);
    }
}
