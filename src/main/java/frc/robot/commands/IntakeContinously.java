
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Command responsible for using 2 joysticks to drive the robot.
 */
public class IntakeContinously extends CommandBase {
    private IntakeSubsystem intake;
    private NetworkTableEntry angle;
    /**
     * Creates a new IntakeContinously command.
     * 
     * @param intake intake subsystem.
     */
    public IntakeContinously(IntakeSubsystem intake,NetworkTableEntry angle) {
        this.addRequirements(intake);
        this.angle = angle;
        this.intake = intake;
    }

    @Override
    public void initialize() {
        this.intake.setIntake(Constants.Intake.IntakeContinously.kSpeed);
        System.out.println(this.angle.getDouble(0));
    }

    @Override
    public boolean isFinished() {
        System.out.println(this.angle.getDouble(0));
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setIntake(0);
    }
}
