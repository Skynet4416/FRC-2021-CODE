package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.Vision;

public class NetworkTables {
    private NetworkTableInstance networkTableInstance;

    private NetworkTable visionTable;
    private NetworkTableEntry visionDistance;
    private NetworkTableEntry visionAngle;

    /**
     * Creates & initializes network tables controller instance.
     */
    public NetworkTables() {
        this.networkTableInstance = NetworkTableInstance.getDefault();
        this.visionTable = this.networkTableInstance.getTable(Vision.kVisionTableKey);
        this.visionDistance = this.visionTable.getEntry(Vision.kVisionDistanceKey);
        this.visionAngle = this.visionTable.getEntry(Vision.kVisionAngleKey);
    }

    /**
     * Returns distance to power port from vision network table.
     */
    public double getVisionDistance() {
        return this.visionDistance.getDouble(0);
    }

    /**
     * Returns angle to power port from vision network table.
     */
    public double getVisionAngle() {
        return this.visionAngle.getDouble(0);
    }
}
