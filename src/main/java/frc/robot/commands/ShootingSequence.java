package frc.robot.commands;
import java.time.LocalTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexingSpinnerSubsystem;
public class ShootingSequence extends CommandBase {
    static LocalTime _start_time;
    public ShootingSequence(IndexingSpinnerSubsystem indexing) {
        this.addRequirements(indexing);
        System.out.println("constructor");
    }
    
    @Override
    public void initialize() {
        /*
        get the angle that you want form vision
        */
        /*
        turn according to the angle (turn to angle command)
        activate the shooter and then activate the indexing loader
        */
    }

    @Override
    public boolean isFinished() {

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        /*disable the shooter and loader */
        }
}
