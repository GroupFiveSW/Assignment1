package decide;

public class LIC {

    HelperFunctions H = new HelperFunctions();

    Decide D = new Decide();




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

}
