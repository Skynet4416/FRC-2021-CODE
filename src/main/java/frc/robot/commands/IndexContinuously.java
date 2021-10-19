import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexingSubsystem;

public class IndexContinuously extends CommandBase {
    private IndexingSubsystem m_indexing;

    public IndexContinuously(IndexingSubsystem indexing) {
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