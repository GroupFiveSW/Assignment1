package decide;

import java.util.Arrays;

public class LIC {
    HelperFunctions H = new HelperFunctions();

    /**
     * Checks whether LIC2 is satisfied
     * Sets <code>Decide.CMV[4]</code> to true/false depending on whether LIC2 is satisfied or not.
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

}
