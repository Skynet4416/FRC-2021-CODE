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
            // double l_trigger = m_controller.getTriggerAxis(Hand.kLeft);
            // double r_trigger = m_controller.getTriggerAxis(Hand.kRight);
            // double turn = m_controller.getX(Hand.kRight);

            // if(l_trigger >= r_trigger) {
            //     if(Math.abs(l_trigger) > 0.1){
            //         if(Math.abs(turn) > 0.1){
            //             double val = (turn > 0 ? 1 : -1) * l_trigger;
            //             m_chassis.set(val, -val);
            //         }else{
            //             m_chassis.set(l_trigger, l_trigger);
            //         }
            //     }else {
            //         m_chassis.set(0, 0);
            //     }
            // } else {
            //     if(Math.abs(r_trigger) > 0.1){
            //         if(Math.abs(turn) > 0.1){
            //             double val = (turn > 0 ? 1 : -1) * r_trigger;
            //             m_chassis.set(-val, val);
            //         }else{
            //             m_chassis.set(-r_trigger, -r_trigger);
            //         }
            //     }else {
            //         m_chassis.set(0, 0);
            //     }
            // }


            double r = m_controller.getY(Hand.kRight);
            double l = m_controller.getY(Hand.kLeft);

            m_chassis.set(l, r);
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
