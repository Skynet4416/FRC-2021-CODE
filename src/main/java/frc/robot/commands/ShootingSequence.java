package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IndexingLoaderSubsytem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootingSequence extends SequentialCommandGroup{
    public ShootingSequence(ShooterSubsystem shooter, IndexingLoaderSubsytem loader,ChassisSubsystem chassis,AHRS AHRS)
    {
        this.addCommands(new TurnToAngle(chassis, AHRS),new ActivateShootingSequenceStart(shooter, loader, chassis,AHRS)); 
    }
    
}
