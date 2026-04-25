package org.firstinspires.ftc.teamcode.lib.util;

public class LinearMapper {

    private final double minIn;
    private final double maxIn;
    private final double minOut;
    private final double maxOut;

    public LinearMapper(double minIn, double maxIn, double minOut, double maxOut) {
        this.minIn = minIn;
        this.maxIn = maxIn;
        this.minOut = minOut;
        this.maxOut = maxOut;
    }

    /**
     * Maps an input value to the output range.
     * @param input The value to be mapped.
     * @param shouldClamp If true, the output will be locked within the output range.
     */
    public double map(double input, boolean shouldClamp) {
        double inputSpan = maxIn - minIn;
        double outputSpan = maxOut - minOut;

        // Prevent division by zero if minIn == maxIn
        if (inputSpan == 0.0) return minOut;

        double result = (input - minIn) * (outputSpan / inputSpan) + minOut;

        if (shouldClamp) {
            if (minOut < maxOut) {
                return Math.max(minOut, Math.min(result, maxOut));
            } else {
                return Math.max(maxOut, Math.min(result, minOut)); // Handles inverted ranges
            }
        } else {
            return result;
        }
    }

    // Optional overload to match Kotlin default parameter
    public double map(double input) {
        return map(input, true);
    }
}