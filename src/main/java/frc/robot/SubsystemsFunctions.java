package frc.robot;

public class SubsystemsFunctions {

    public static boolean validPower(double power) {
        if (Math.abs(power) > 1) {
            // On invalid value, print error and return.
            return false;
        }
        return true;
    }    

}
