package frc.robot.Meth_tools;

import frc.robot.Constants;

public final class MethTools{
    public static double PController(double target, double current,double Kp, double startdiff,double minvalue, double maxvalue, Point[] kB){
        double currentdiff = (target - current);
        if(target < current){
            maxvalue = -maxvalue;
            minvalue = -minvalue;
        }
        double retval = Math.min(1,Math.max(Kp*(currentdiff/startdiff),-1));
        if(retval > 0)
            retval = Math.max(0.12,Math.abs(retval));
        else
            retval = -Math.max(0.12,Math.abs(retval));
        // double retval = Math.min(maxvalue,Math.max(Kp*CubicPValue(1-(currentdiff/startdiff), kB[0],kB[1],kB[2],kB[3]) *(currentdiff/startdiff),minvalue));
        // System.out.println(target + " , " + current + " , " + startdiff + " , " + currentdiff +" , " + retval);
        // System.out.println(Kp + " * " + (currentdiff/startdiff) + " * " +CubicPValue(1-Math.abs(currentdiff/startdiff), kB[0],kB[1],kB[2],kB[3]) + " = " +retval);
        return retval;
    }
    public static double CubicPValue(double i, Point kA,Point kB,Point kC,Point kD)//dark sourcery source tomer barzeli (0528408878,https://github.com/tomerBARZ, tserver.serverpit.com, 326673191)  
    {
        i = Math.min((double)(4/3),Math.max(i,(double)(1/1000)));
        //level1
        Point level1Point1 = new Point(i,((kA.y -kB.y)/(kA.x-kB.x))*i+kA.y);
        Point level1Point2 = new Point(i+kB.x,((kB.y -kC.y)/(kB.x-kC.x))*i+kB.y);
        Point level1Point3 = new Point(i+kC.x,((kC.y -kD.y)/(kC.x-kD.x))*i+kC.y); 
        //level2
        Point level2Point1 = new Point(i+level1Point1.x,((level1Point1.y-level1Point2.y)/(level1Point1.x-level1Point2.x))*i+level1Point1.y);
        Point level2Point2 = new Point(i+level1Point2.x,((level1Point2.y-level1Point3.y)/(level1Point2.x-level1Point3.x))*i+level1Point2.y);
        //level3
        Point level3Point1 = new Point(i+level2Point1.x,((level2Point1.y-level2Point2.y)/(level2Point1.x-level2Point2.x))*i+level2Point1.y);
        return level3Point1.y;
    }
    public static double GetShooterSpeed(double distance)
    {
        double angle = Constants.Shooter.angle;
        double rads = Math.toRadians(angle);
        double stright_line_distance = distance * Math.cos(rads);
        double hight = distance * Math.cos(rads)-0.54;
        double speed = 60*Math.sqrt((9.8*Math.pow(stright_line_distance,2))/(Math.sin(2*rads)*stright_line_distance - 2*Math.pow(Math.cos(rads),2)*hight)) / (Math.PI*2*0.14);
        System.out.println(speed);

        return speed;
    }
     
}