package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
public class IndexingReset extends CommandBase {
    // public IndexingReset(IndexingSpinnerSubsystem indexing) {
    //     this.addRequirements(indexing);
    //     this.m_indexing = indexing;
    //     System.out.println("constructor");
    // }
    @Override
    public void end(boolean interrupted) {
        Globals.released = true;
    }
}
