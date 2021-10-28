package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexingLoaderSubsytem;

public class LoadIntoShooter extends CommandBase {
    private IndexingLoaderSubsytem m_indexing;

    public LoadIntoShooter(IndexingLoaderSubsytem indexingSubsystem) {
        this.addRequirements(indexingSubsystem);
        this.m_indexing = indexingSubsystem;
    }

    @Override
    public void initialize() {
        this.m_indexing.setLoader(Constants.Indexing.kLoadingSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.m_indexing.setLoader(0);
    }
}
