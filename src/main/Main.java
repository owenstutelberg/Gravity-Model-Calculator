package main;

import java.awt.Color;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Set<Double> gravities = new TreeSet<>();
        for (City cityOne : City.values()) {
            for (City cityTwo : City.values()) {
                if (cityOne == cityTwo) continue;

                double gravity = cityOne.getGravity(cityTwo);

                // System.out.println("Gravity between " + cityOne + " and " + cityTwo + ": " + gravity);
                gravities.add(Math.floor(gravity));
            }
        }
        System.out.println(gravities);
    }

    public static Color getColor(City cityOne, City cityTwo) {
        double t = (cityOne.getGravity(cityTwo) - getMinGravity()) / (getMaxGravity() - getMinGravity());

        int r = (int) (255 * t);
        int g = (int) (255 * (1 - t));
        int b = 0;

        return new Color(r, g, b);
    }

    public static double getMaxGravity() {
        double max = Integer.MIN_VALUE;

        for (City cityOne : City.values()) {
            for (City cityTwo : City.values()) {
                if (cityOne == cityTwo) continue;
                if (cityOne == City.MINNEAPOLIS && cityTwo == City.SAINT_PAUL) continue;

                double gravity = cityOne.getGravity(cityTwo);
                if (gravity > max) max = gravity;
            }
        }

        return max;
    }

    public static double getMinGravity() {
        double min = Integer.MAX_VALUE;

        for (City cityOne : City.values()) {
            for (City cityTwo : City.values()) {
                if (cityOne == cityTwo) continue;

                double gravity = cityOne.getGravity(cityTwo);
                if (gravity < min) min = gravity;
            }
        }

        return min;
    }


}
