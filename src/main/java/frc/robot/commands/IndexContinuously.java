package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexingSpinnerSubsystem;

public class IndexContinuously extends CommandBase {
    private IndexingSpinnerSubsystem m_indexing;

    public IndexContinuously(IndexingSpinnerSubsystem indexing) {
        this.addRequirements(indexing);
        this.m_indexing = indexing;
    }

    @Override
    public void initialize() {
        this.m_indexing.setSpinner(Constants.Indexing.kSpinSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.m_indexing.setSpinner(0);
    }
}