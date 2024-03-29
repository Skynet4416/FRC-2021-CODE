
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem;

/**
 * Command responsible for lowering each arm individually or simultaneously
 */
public class LowerClimb extends CommandBase {
    private ClimbSubsystem climb;
    private boolean right;
    private boolean left;

    /**
     * Creates a new IntakeContinously command.
     * 
     * @param climb intake subsystem.
     */
    public LowerClimb(ClimbSubsystem climb, boolean right, boolean left) {
        this.addRequirements(climb);
        this.climb = climb;
        this.left = left;
        this.right = right;
    }

    @Override
    public void initialize() {
        if (this.right && this.left) {
            this.climb.setRight(Constants.Climb.kLowerSpeed);
            this.climb.setLeft(Constants.Climb.kLowerSpeed);
        } else if (right) {
            this.climb.setRight(Constants.Climb.kLowerSpeed);
        } else if (left) {
            this.climb.setLeft(Constants.Climb.kLowerSpeed);
        } else {
            System.out.println("Error! You have to choose an arm");
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (this.right && this.left) {
            this.climb.setRight(0);
            this.climb.setLeft(0);
        } else if (right) {
            this.climb.setRight(0);
        } else if (left) {
            this.climb.setLeft(0);
        } else {
            System.out.println("Error! You have to choose an arm");
        }

    }
}
