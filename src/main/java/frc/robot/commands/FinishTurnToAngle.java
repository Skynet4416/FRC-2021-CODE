package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
public class FinishTurnToAngle extends CommandBase {

    @Override
    public void end(boolean interapted) {
        Globals.joysticksControlEnbaled = true;
        System.out.println("end");

    }
}
