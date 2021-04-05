package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Intake;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntake extends CommandBase {
    private IntakeSubsystem k_intake;

    /**
     * Creates a new ExtendIntake command.
     * 
     * @param climb Intake subsystem.
     */
    public ExtendIntake(IntakeSubsystem intake) {
        this.addRequirements(intake);
        this.k_intake = intake;
    }

    @Override
    public void initialize() {
        this.k_intake.setDeploy(Intake.kDeployPower);
    }

    @Override
    public boolean isFinished() {
        return k_intake.getDeployCurrent() > Intake.kMaxDeployCurrent;
    }

    @Override
    public void end(boolean interrupted) {
        this.k_intake.setDeploy(0);
    }
}
