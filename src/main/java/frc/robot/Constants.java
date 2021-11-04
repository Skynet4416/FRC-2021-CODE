// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public class Chassis {
        public final static double kThershold = 2;
        public final static double kTurnPowerPrecentage = 0.1;

        public class Motors {
            public static final int kMasterLeft = 11;
            public static final int kSlaveLeft = 12;
            public static final int kMasterRight = 10;
            public static final int kSlaveRight = 13;
        }
    }

    public class Intake {
        public static final double kDeployPower = 1;
        public static final double kMaxDeployCurrent = 40; 

        public class Motors {
            public static final int kDeploy = 100;
            public static final int kIntake = 20;
        }

        public class IntakeContinously {
            public static final double kSpeed = 0.55;
        }
    }
  

    public class Indexing {
        public static final double kSpinSpeed = 0.2;
        public static final double KSuperSpeed = 0.2;
        public static final double kLoadingSpeed = 1;
        public static final int kTimeLimitSeconds = 412;

        public class Motors {
            public static final int kSpinner = 30;
            public static final int kLoader = 40;
        }
    }    

    public class Climb {
        public static final int kRight = 15;
        public static final int kLeft = 14;
        public static final int kHallEffect = 16;
        public static final int kLowerSpeed = -1;
        public static final int kClimbMaxHeightSpeed = -1;
    }

    public class Controls {
        public static final int kLeftJoy = 2;
        public static final int kRightJoy = 1;
        public static final int kSystemsController = 0;
    }

    public class Shooter {
        public static final int kShooterMaster = 41;
        public static final int kShooterSlave = 42;
        public static final double kFallbackShooterSpeed = 1;

        public class PID {
            public static final double kP = 0.00003;
            public static final double kI = 0.00000003;
            public static final double kD = 0.03;
            public static final double kF = 0.000173;
            public static final double kIZone = 3000;
        }

        public class SmartDashboard {
            public static final String ShooterKP = "Shooter kP";
            public static final String ShooterKI = "Shooter kI";
            public static final String ShooterKD = "Shooter kD";
            public static final String ShooterKF = "Shooter kF";
            public static final String ShooterSetpoint = "Shooter Setpoint";
        }
    }

    public class Vision {
        public static final String kVisionTableKey = "Vision";
        public static final String kVisionDistanceKey = "distance";
        public static final String kVisionAngleKey = "angle";
    }
}

