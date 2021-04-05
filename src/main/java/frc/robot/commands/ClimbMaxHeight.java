package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.Constants;

/**
 * Command responsible for moving both climb arms to their max height (until the
 * hall effect sensor is triggered). Activated by pressing Y on the Xbox
 * Controller.
 */
public class ClimbMaxHeight extends CommandBase {
    private ClimbSubsystem k_climb;

    /**
     * Creates a new ClimbMaxHeight command.
     * 
     * @param climb Climb subsystem.
     */
    public ClimbMaxHeight(ClimbSubsystem climb) {
        this.addRequirements(climb);
        this.k_climb = climb;
    }

    @Override
    public void initialize() {
        this.k_climb.setLeft(Constants.Climb.kClimbMaxHeightSpeed);
        this.k_climb.setRight(Constants.Climb.kClimbMaxHeightSpeed);
    }

    @Override
    public boolean isFinished() {
        return this.k_climb.getHallEffectStatus();
    }

    @Override
    public void end(boolean interrupted) {
        this.k_climb.setLeft(0);
        this.k_climb.setRight(0);
    }
}
