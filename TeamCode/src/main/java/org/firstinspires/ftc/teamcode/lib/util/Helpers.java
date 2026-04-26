package org.firstinspires.ftc.teamcode.lib.util;

public class Helpers {
    public static double wrap(double radians) {
        double twoPi = 2.0 * Math.PI;

        // Use the modulo operator to get the remainder
        double wrapped = radians % twoPi;

        // If the result is negative, add 2pi to bring it into the positive range
        if (wrapped < 0) {
            wrapped += twoPi;
        }

        return wrapped;
    }
}
