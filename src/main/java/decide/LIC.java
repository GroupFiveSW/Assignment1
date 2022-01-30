package decide;

import java.util.Arrays;

public class LIC {

    HelperFunctions H = new HelperFunctions();




    /**
     * Evaluates whether LIC1 is satisfied.
     *Sets <Code>Decide.CMV[1]</Code> to true if satisified, else to false.
     */

    public void condition1(){

        double[] xCoords = Decide.X2;
        double[] yCoords = Decide.Y2;

        for(int i = 0; i<=Decide.NUMPOINTS2-3;i++){
            double[] point1 = {xCoords[i], yCoords[i]};
            double[] point2 = {xCoords[i+1],yCoords[i+1]};

            double[] point3 = {xCoords[i+2], yCoords[i+2]};

            double[][] triplet = {point1,point2,point3};

            if(!H.insideCircle(Decide.PARAMETERS2.RADIUS1, triplet)) {
                Decide.CMV2[1] = true;
                return;
            }
        }
        Decide.CMV2[1] = false;
        return;
    }


    /**
     * Checks whether LIC 0 is satisfied. Meaning if it exists at least one set of two consecutive data points in 2D array that are a distance greater than
     * the length, LENGTH1, apart.
     * (0 ≤ LENGTH1)
     */
    public void condition0() {

        double [] p1 = new double[2];
        double [] p2 = new double[2];

        for (int i = 0; i < Decide.X2.length - 1; ++i) {
            p1[0] = Decide.X2[i];
            p1[1] = Decide.Y2[i];

            p2[0] = Decide.X2[i+1];
            p2[1] = Decide.Y2[i+1];
            if(HelperFunctions.euclideanDistance(p1,p2) > Decide.PARAMETERS2.LENGTH1){
                Decide.CMV2[0] = true;
                return;
            }
        }
        Decide.CMV2[0] = false;

    }

    /**
     * Checks whether LIC2 is satisfied
     * Sets <code>Decide.CMV[2]</code> to true/false depending on whether LIC2 is satisfied or not.
     */
    public void condition2() {
        boolean conditionSatisfied = false;

        // Iterate through all sets of three consecutive points
        for (int startIndex = 0; startIndex <= Decide.NUMPOINTS2 - 3; startIndex++) {
            double[] p1 = {Decide.X2[startIndex], Decide.Y2[startIndex]};
            double[] p2 = {Decide.X2[startIndex+1], Decide.Y2[startIndex+1]};
            double[] p3 = {Decide.X2[startIndex+2], Decide.Y2[startIndex+2]};

            double angle = H.getAngle(p1,p2,p3);
            if (angle < (Decide.PI - Decide.PARAMETERS2.EPSILON) || angle > (Decide.PI + Decide.PARAMETERS2.EPSILON)) {
                // Check if first or last point coincides with vertex p2
                if (Arrays.equals(p1,p2) || Arrays.equals(p2,p3)) {
                    conditionSatisfied = false;
                } else {
                    conditionSatisfied = true;
                    break;
                }

            }

        }
        Decide.CMV2[2] = conditionSatisfied;
    }

    /**
     * Checks whether LIC 3 is satisfied.
     * Sets <code>Decide.CMV[3]</code> to result
     */
    public void condition3() {


        double[] xCoords = Decide.X2;
        double[] yCoords = Decide.Y2;

        for (int startIndex = 0; startIndex <= Decide.NUMPOINTS2-3; startIndex++) {

            double[][] points ={{xCoords[startIndex], yCoords[startIndex]},
                                {xCoords[startIndex+1], yCoords[startIndex+1]},
                                {xCoords[startIndex+2], yCoords[startIndex+2]}};
            if (H.calcTriangleArea(points[0], points[1], points[2]) > Decide.PARAMETERS2.AREA1) {
                Decide.CMV2[3] = true;
                return;
            }
        }
        Decide.CMV2[3] = false;
    }

    Decide D = new Decide();





    /**
     * Checks whether LIC 4 is satisfied.
     * Sets <code>Decide.CMV[4]</code> to result.
     */
    public void condition4() {
        double[] xCoords = Decide.X2;
        double[] yCoords = Decide.Y2;
        int nrOfPoints = Decide.PARAMETERS2.Q_PTS;
        int requiredNrOfQuadrants = Decide.PARAMETERS2.QUADS;

        // Iterate over all consecutive ranges of length nrOfPoints
        for (int startIndex = 0; startIndex <= Decide.NUMPOINTS2 - nrOfPoints; startIndex++) {
            int endIndex = startIndex + nrOfPoints - 1;

            // Find all quadrants used by points in the range
            boolean[] usedQuadrants = new boolean[4];
            for (int point = startIndex; point <= endIndex; point++) {
                if (yCoords[point] >= 0) {
                    if (xCoords[point] >= 0) {
                        usedQuadrants[0] = true;
                    } else {
                        usedQuadrants[1] = true;
                    }
                } else {
                    if (xCoords[point] <= 0) {
                        usedQuadrants[2] = true;
                    } else {
                        usedQuadrants[3] = true;
                    }
                }
            }

            // Count quadrants and make sure it is more than the input.
            int nrOfQuadrants = (usedQuadrants[0]?1:0) + (usedQuadrants[1]?1:0)
                    + (usedQuadrants[2]?1:0) + (usedQuadrants[3]?1:0);
            if (nrOfQuadrants > requiredNrOfQuadrants) {
                Decide.CMV2[4] = true;
                return;
            }
        }

        Decide.CMV2[4] = false;
    }


    /**
     * Checks whether LIC 5 is satisfied.
     * Sets <code>Decide.CMV[5]</code> to result.
     */
    public void condition5() {
        double[] xCoords = Decide.X2;

        // Iterate over all consecutive ranges of length 2
        for (int firstIndex = 0; firstIndex <= Decide.NUMPOINTS2 - 2; firstIndex++) {
            int secondIndex = firstIndex + 1;
            if (xCoords[secondIndex] - xCoords[firstIndex] < 0) {
                Decide.CMV2[5] = true;
                return;
            }
        }

        Decide.CMV2[5] = false;
    }

    /**
     * Checks whether LIC 8 is satisfied.
     * Sets <code>Decide.CMV2[8]</code> to result.
     */
    public void condition8() {
        double[] xCoords = Decide.X2;
        double[] yCoords = Decide.Y2;

        int A_PTS = Decide.PARAMETERS2.A_PTS;
        int B_PTS = Decide.PARAMETERS2.B_PTS;

        // Starting condition
        if (Decide.NUMPOINTS2 < 5 ){
            Decide.CMV2[8] = false;
            return;
        }

        // Iterate through sets of three points and check the LIC.
        for (int startIndex = 0; startIndex <= Decide.NUMPOINTS2 - (A_PTS + B_PTS + 3); startIndex++) {
            // List of three points where each point is a list of x,y cords. The points are separated by exactly A_PTS and B_PTS respectively.
            double[][] points ={{xCoords[startIndex], yCoords[startIndex]},
                                {xCoords[startIndex + A_PTS + 1 ], yCoords[startIndex + A_PTS + 1]},
                                {xCoords[startIndex+ A_PTS + B_PTS + 2], yCoords[startIndex + A_PTS + B_PTS + 2]}};

            if(!H.insideCircle(Decide.PARAMETERS2.RADIUS1, points)) {
                Decide.CMV2[8] = true;
                return;
            }
        }
        Decide.CMV2[8] = false;
        return;
    }
    /**
     * Checks whether LIC 10 is satisfied.
     * Sets <code>Decide.CMV2[10]</code> to true if LIC 10 is satisfied or false if not.
     */
    public void condition10() {
        double[] xCoords = Decide.X2;
        double[] yCoords = Decide.Y2;
        int firstIntervening = Decide.PARAMETERS2.E_PTS;
        int secondIntervening = Decide.PARAMETERS2.F_PTS;

        if (Decide.NUMPOINTS2 < 5) {
            Decide.CMV2[10] = false;
            return;
        }

        int totalInterval = 1 + firstIntervening + 1 + secondIntervening + 1;

        for (int firstPoint = 0; firstPoint <= Decide.NUMPOINTS2 - totalInterval; firstPoint++) {
            int secondPoint = firstPoint + firstIntervening + 1;
            int thirdPoint = secondPoint + secondIntervening + 1;
            double[][] points ={{xCoords[firstPoint], yCoords[firstPoint]},
                    {xCoords[secondPoint], yCoords[secondPoint]},
                    {xCoords[thirdPoint], yCoords[thirdPoint]}};
            if (H.calcTriangleArea(points[0], points[1], points[2]) > Decide.PARAMETERS2.AREA1) {
                Decide.CMV2[10] = true;
                return;
            }
        }
        Decide.CMV2[10] = false;
    }
}
