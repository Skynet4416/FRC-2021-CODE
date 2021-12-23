package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;

/**
 * Command responsible for using 2 joysticks to drive the robot using arcade-like drive
 */
public class DriveArcadeController extends CommandBase {
    private ChassisSubsystem m_chassis;
    private XboxController m_controller;
    private DoubleSupplier m_limiter;

    /**
     * Creates a new DriveArcadeController command.
     * 
     * @param chassis Chassis subsystem.
     * @param X       Supplier for the X input.
     * @param Y       Supplier for the Y input.
     * @param limiter Supplier for the throutle
     */
    public DriveArcadeController(ChassisSubsystem chassis, XboxController controller) {
        this.addRequirements(chassis);
        this.m_chassis = chassis;
        this.m_controller = controller;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Globals.joysticksControlEnbaled){
            double x = m_controller.getX(Hand.kLeft);
            double y = m_controller.getY(Hand.kLeft);
            x = Math.abs(x) * x;
            y = Math.abs(y) * y;
            
            m_chassis.set(y + x, y - x);
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
