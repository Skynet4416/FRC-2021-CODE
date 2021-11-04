package frc.robot.commands;
import static java.time.temporal.ChronoUnit.MILLIS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IndexingSpinnerSubsystem;
import frc.robot.Globals;
import java.time.LocalTime;
public class IndexingSpinForOneSlot extends CommandBase {
    private IndexingSpinnerSubsystem m_indexing;
    static LocalTime _start_time;
    public IndexingSpinForOneSlot(IndexingSpinnerSubsystem indexing) {
        this.addRequirements(indexing);
        this.m_indexing = indexing;
    }

    @Override
    public void initialize() {
        if(Globals.released){
            Globals.released = false;
            IndexingSpinForOneSlot._start_time = LocalTime.now();
            this.m_indexing.setSpinner(Constants.Indexing.KSuperSpeed);
        }

    }

    @Override
    public boolean isFinished() {
        
        long currTime = Math.abs(MILLIS.between(LocalTime.now(), _start_time));
        return currTime>Constants.Indexing.kTimeLimitSeconds;
    }

    @Override
    public void end(boolean interrupted) {

        this.m_indexing.setSpinner(0);
    }
}
