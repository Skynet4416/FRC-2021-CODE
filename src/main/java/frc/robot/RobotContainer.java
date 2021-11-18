// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.Shooter;
import frc.robot.commands.ActivateShootingSequenceStart;
import frc.robot.commands.DriveByJoy;
import frc.robot.commands.DriveBySingleJoy;
// import frc.robot.commands.ExtendIntake;
import frc.robot.commands.IndexContinuously;
import frc.robot.commands.IndexingReset;
import frc.robot.commands.IndexingSpinForOneSlot;
import frc.robot.commands.IntakeContinously;
import frc.robot.commands.LoadIntoShooter;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IndexingLoaderSubsytem;
import frc.robot.subsystems.IndexingSpinnerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.subsystems.ClimbSubsystem;
// import frc.robot.commands.LowerClimb;
// import frc.robot.commands.ClimbMaxHeight;
import frc.robot.commands.ShooterSpinUp;
import frc.robot.commands.ShootingSequence;
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
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable visionTable = inst.getTable("Vision");
  private NetworkTableEntry angleFromTargetEntry = visionTable.getEntry("aaa");
  private NetworkTableEntry distanceFromTargetEntry = visionTable.getEntry("DistanceFromTarget");
  // The robot's subsystems and commands are defined here...
  private final ChassisSubsystem m_chassis = new ChassisSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final IndexingLoaderSubsytem m_indexing_loader = new IndexingLoaderSubsytem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final IndexingSpinnerSubsystem m_indexing_spinner = new IndexingSpinnerSubsystem();
  // private final ClimbSubsystem m_climb = new ClimbSubsystem();
  private final AHRS m_ahrs; 

  private final Joystick m_leftJoy = new Joystick(Constants.Controls.kLeftJoy);
  private final Joystick m_rightJoy = new Joystick(Constants.Controls.kRightJoy);

  private final XboxController m_systemsController = new XboxController(Constants.Controls.kSystemsController);

  private final JoystickButton m_intake_button = new JoystickButton(m_systemsController,
      XboxController.Button.kA.value);
  // private final JoystickButton m_deploy_intake_button = new JoystickButton(m_systemsController,
  //     XboxController.Button.kY.value);

  private final JoystickButton m_indexingLoadButton = new JoystickButton(m_systemsController,
      XboxController.Button.kB.value);
  private final JoystickButton m_shooterSpinUp = new JoystickButton(m_systemsController,
      XboxController.Button.kX.value);

  // private final JoystickButton m_lower_left_arm = new JoystickButton(m_systemsController,
  //     XboxController.Button.kBumperLeft.value);
  // private final JoystickButton m_lower_right_arm = new JoystickButton(m_systemsController,
  //     XboxController.Button.kBumperRight.value);
  // private final JoystickButton m_climbMax_button = new JoystickButton(m_systemsController,
  //     XboxController.Button.kY.value);

  private final JoystickButton m_indexing_spinner_button = new JoystickButton(m_systemsController, XboxController.Button.kY.value);
  private final JoystickButton m_shooting_sequence_button = new JoystickButton(m_systemsController, XboxController.Button.kBumperRight.value);
  private final JoystickButton m_indexing_spin_one_slot_button = new JoystickButton(m_systemsController,XboxController.Button.kBumperLeft.value);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_ahrs = new AHRS(SPI.Port.kMXP); // navX init

    // Configure the button bindings
    configureButtonBindings();
    configureSmartDashBoard();
    // Set default commands.
    if(Constants.Controls.drivingControllerScheme == "TwoJoy")
      m_chassis.setDefaultCommand(new DriveByJoy(m_chassis, m_leftJoy::getY, m_rightJoy::getY));
    else if(Constants.Controls.drivingControllerScheme == "SingleJoy")
      m_chassis.setDefaultCommand(new DriveBySingleJoy(m_chassis, m_leftJoy::getY, m_leftJoy::getTwist));
  }

  private void configureSmartDashBoard() {
    SmartDashboard.putNumber(Constants.Shooter.SmartDashboard.ShooterSetpoint, 0);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointAx, 0);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointBx, 0.3333333);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointCx,2* 0.33333333);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointDx, 1);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointAy, 1);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointCy, 1);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointBy, 0.5);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAnglePointDy, 0);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.kP, Constants.Chassis.kP);
    SmartDashboard.putNumber(Constants.Chassis.SmartDashboard.TurnAngleThreshold, Constants.Chassis.kThershold);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    this.m_intake_button.whileHeld(new IntakeContinously(this.m_intake));
    this.m_indexingLoadButton.whileHeld(new LoadIntoShooter(this.m_indexing_loader));
    this.m_shooterSpinUp.whileHeld(new ShooterSpinUp(this.m_shooter, () -> Shooter.kFallbackShooterSpeed));
    this.m_indexing_spinner_button.whileHeld(new IndexContinuously(this.m_indexing_spinner));
    // this.m_shooting_sequence_button.whileHeld(new ActivateShootingSequenceStart(this.m_shooter,this.m_indexing_loader,this.m_chassis,angleFromTargetAntry.getDouble(m_ahrs.getAngle()), m_ahrs));
    this.m_indexing_spin_one_slot_button.whileHeld(new IndexingReset());
    this.m_indexing_spin_one_slot_button.whileHeld(new IndexingSpinForOneSlot(this.m_indexing_spinner));
    // this.m_deploy_intake_button.whileHeld(new ExtendIntake(this.m_intake));
    // this.m_lower_left_arm.whileHeld(new LowerClimb(this.m_climb, false, true));
    // this.m_lower_right_arm.whileHeld(new LowerClimb(this.m_climb, true, false));
    // this.m_climbMax_button.whenPressed(new ClimbMaxHeight(this.m_climb));
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
