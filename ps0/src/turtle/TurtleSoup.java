/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * You may not modify those values:
     * Turning degree is 90.
     * Number of steps the number square sides (4)
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        // throw new RuntimeException("implement me!");

        final int degree = 90;
        int steps = 4;

        while (steps != 0) {

            turtle.forward(sideLength);
            turtle.turn(degree);
            steps--;
        }

                    
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * 180.0 is the degree of a straight line
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        final double lineDegree = 180.0;
        return lineDegree * (sides - 2) / sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * sum is the total sum of exterior angles of any polygon
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        final int sum = 360;
        return (int)Math.ceil(sum / (180 - angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * turning should be by exterior angle, which is 180 - interior angle
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        final int exterior = 180;
        int steps = sides;
        while (steps != 0) {
            turtle.forward(sideLength);
            turtle.turn(exterior - calculateRegularPolygonAngle(sides));
            steps--;
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {

            final int fullCirc = 360;

            final int deltaX = targetX - currentX;
            final int deltaY = targetY - currentY;
            
            final double atan2 = Math.toDegrees(Math.atan2(deltaX, deltaY));

            final double head2point = (atan2 - currentHeading + fullCirc) % fullCirc;

            return head2point;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> list = new ArrayList<>();
        double currentHeading = 0;
        for (int i = 0; i < (xCoords.size() - 1); i++) {
            
            list.add(calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i), 
            xCoords.get(i+1), yCoords.get(i+1)));

            // adjusting the new heading (with each move the turtle change its heading)
            currentHeading = (currentHeading + list.get(i) + 360) % 360;

        }

        return list;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        // TODO: Implement this.
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        // drawSquare(turtle, 40);

        // drawRegularPolygon(turtle, 5, 100);
        
        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
