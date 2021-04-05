
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Command responsible for using 2 joysticks to drive the robot.
 */
public class IntakeContinously extends CommandBase {
    private IntakeSubsystem intake;

    /**
     * Creates a new IntakeContinously command.
     * 
     * @param intake intake subsystem.
     */
    public IntakeContinously(IntakeSubsystem intake) {
        this.addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void initialize() {
        this.intake.setIntake(Constants.Intake.IntakeContinously.kSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.setIntake(0);
    }
}
