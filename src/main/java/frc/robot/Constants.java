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
        public class Motors {
            public static final int kMasterLeft = 11;
            public static final int kSlaveLeft = 12;
            public static final int kMasterRight = 10;
            public static final int kSlaveRight = 13;
        }
    }

    public class Controls {
        public static final int kLeftJoy = 2;
        public static final int kRightJoy = 1;
        public static final int kSystemsController = 0;
    }
    public class Climb {
        public static final int kRight = 15;
        public static final int kLeft = 14;
        public static final int kHallEffect = 16;
    }
}