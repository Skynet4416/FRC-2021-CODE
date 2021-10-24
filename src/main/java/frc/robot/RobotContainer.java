// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.Shooter;
import frc.robot.commands.DriveByJoy;
import frc.robot.commands.ExtendIntake;
import frc.robot.commands.IntakeContinously;
import frc.robot.commands.LoadIntoShooter;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IndexingSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.ClimbSubsystem;
// import frc.robot.commands.LowerClimb;
// import frc.robot.commands.ClimbMaxHeight;
import frc.robot.commands.ShooterSpinUp;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.commands.TurnToAngle;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ChassisSubsystem m_chassis = new ChassisSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final IndexingSubsystem m_indexing = new IndexingSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  // private final ClimbSubsystem m_climb = new ClimbSubsystem();
  private final AHRS m_ahrs; 

  private final Joystick m_leftJoy = new Joystick(Constants.Controls.kLeftJoy);
  private final Joystick m_rightJoy = new Joystick(Constants.Controls.kRightJoy);

  private final XboxController m_systemsController = new XboxController(Constants.Controls.kSystemsController);

  private final JoystickButton m_intake_button = new JoystickButton(m_systemsController,
      XboxController.Button.kA.value);
  private final JoystickButton m_deploy_intake_button = new JoystickButton(m_systemsController,
      XboxController.Button.kY.value);

  private final JoystickButton m_indexingLoadButton = new JoystickButton(m_systemsController,
      XboxController.Button.kB.value);
  private final JoystickButton m_shooterSpinUp = new JoystickButton(m_systemsController,
      XboxController.Button.kX.value);

  private final JoystickButton m_lower_left_arm = new JoystickButton(m_systemsController,
      XboxController.Button.kBumperLeft.value);
  private final JoystickButton m_lower_right_arm = new JoystickButton(m_systemsController,
      XboxController.Button.kBumperRight.value);
  private final JoystickButton m_climbMax_button = new JoystickButton(m_systemsController,
      XboxController.Button.kY.value);
 
  // private final JoystickButton turn_test_button = new JoystickButton(m_systemsController,
  //     XboxController.Button.kB.value);
  

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_ahrs = new AHRS(SPI.Port.kMXP); // navX init

    // Configure the button bindings
    configureButtonBindings();

    // Set default commands.
    //m_chassis.setDefaultCommand(new DriveByJoy(m_chassis, m_leftJoy::getY, m_rightJoy::getY));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    this.m_intake_button.whileHeld(new IntakeContinously(this.m_intake));
    this.m_indexingLoadButton.whileHeld(new LoadIntoShooter(this.m_indexing));
    this.m_shooterSpinUp.whileHeld(new ShooterSpinUp(this.m_shooter, () -> Shooter.kFallbackShooterSpeed));
    // this.m_deploy_intake_button.whileHeld(new ExtendIntake(this.m_intake));

    // this.m_lower_left_arm.whileHeld(new LowerClimb(this.m_climb, false, true));
    // this.m_lower_right_arm.whileHeld(new LowerClimb(this.m_climb, true, false));
    // this.m_climbMax_button.whenPressed(new ClimbMaxHeight(this.m_climb));
    
    // this.turn_test_button.whenPressed(new TurnToAngle(110, m_ahrs, m_chassis));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
