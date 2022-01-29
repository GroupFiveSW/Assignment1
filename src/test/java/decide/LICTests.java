package decide;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LICTests {

    /**
     * Test LIC 2 with 3 points that form an angle of 90 degrees.
     * As such LIC 2 should be satisfied since 90 degrees is > PI+EPSILON
     */
    @Test
    void condition2Satisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{0,0,1};
        Decide.Y2 = new double[]{1,0,0};
        Decide.PARAMETERS2.EPSILON = Math.pow(10,-3);
        Decide.NUMPOINTS2 = 3;

        lic.condition2();

        assertEquals(true, Decide.CMV2[2]);
    }

    /**
     * Test LIC 2 with three points in which the last point coincides with vertex.
     * As such LIC 2 should not be satisfied.
     */
    @Test
    void condition2NotSatisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[] {0,1,1};
        Decide.Y2 = new double[] {0,0,0};
        Decide.PARAMETERS2.EPSILON = Math.pow(10, -3);
        Decide.NUMPOINTS2 = 3;

        lic.condition2();
        assertFalse(Decide.CMV2[2]);
    }


    /**
     * Tests LIC 4 with 2 points in 2 quadrants, requiring more than 1 quadrant.
     * 2 > 1 meaning it should be satisfied.
     */
    @Test
    void condition4Satisfied() {
        LIC lic = new LIC();

        Decide.PARAMETERS2.Q_PTS = 2;
        Decide.PARAMETERS2.QUADS = 1;
        Decide.X2 = new double[]{2, 3};
        Decide.Y2 = new double[]{3, -1};
        Decide.NUMPOINTS2 = 2;

        lic.condition4();

        assertEquals(true, Decide.CMV2[4], "Condition 4 should be satisfied");
    }

    /**
     * Tests LIC 4 with 2 points in 1 quadrant, where one point is an edge case.
     * 1 == 1 meaning it should not be satisfied.
     */
    @Test
    void condition4NotSatisfied() {
        LIC lic = new LIC();

        Decide.PARAMETERS2.Q_PTS = 2;
        Decide.PARAMETERS2.QUADS = 1;
        Decide.X2 = new double[]{0, -1};
        Decide.Y2 = new double[]{-2, -1};
        Decide.NUMPOINTS2 = 2;

        lic.condition4();

        assertEquals(false, Decide.CMV2[4], "Condition 4 should not be satisfied");
    }
}