package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
public class IndexingReset extends CommandBase {

    @Override
    public void initialize() {
        Globals.released = true;
    }
}
