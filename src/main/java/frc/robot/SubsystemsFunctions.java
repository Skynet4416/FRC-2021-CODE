package frc.robot;

public class SubsystemsFunctions {

    public static boolean validPower(double power) {
        if (Math.abs(power) > 1) {
            // On invalid value, print error and return.
            System.out.println("value for the engine isn't in range");
            return false;
        }
        return true;
    }    

}
