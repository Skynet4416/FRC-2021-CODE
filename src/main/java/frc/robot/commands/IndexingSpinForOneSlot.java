package frc.robot.commands;
import static java.time.temporal.ChronoUnit.SECONDS;
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
        System.out.println("constructor");
    }

    @Override
    public void initialize() {
        if(Globals.released){
            Globals.released = false;
            System.out.println("initialize");
            IndexingSpinForOneSlot._start_time = LocalTime.now();
            this.m_indexing.setSpinner(Constants.Indexing.kSpinSpeed);
        }

    }

    @Override
    public boolean isFinished() {
        
        long currTime = Math.abs(SECONDS.between(LocalTime.now(), _start_time));
        System.out.println(currTime);
        return currTime>Constants.Indexing.kTimeLimitSeconds;
    }

    @Override
    public void end(boolean interrupted) {

        this.m_indexing.setSpinner(0);
    }
}
