package decide;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LICTests {

    HelperFunctions helper = new HelperFunctions();

    Random rand = new Random();



    /**
     *Tests LIC 1 with a sequence of NUMPOINTS planar points with a radius unable to collect
     * any subsequence of three points in any circle of that radius.
     */
    @Test
    void condition1Satisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = rand.nextInt(60) + 40;
        Decide.X2 = new double[100];
        Decide.Y2 = new double[100];

        for(int i=0; i<100; i++){
            Decide.X2[i] = 3*i-50;
            Decide.Y2[i] = 3*i-50;
        }

        Decide.PARAMETERS2.RADIUS1 = 4;
        lic.condition1();

        assertTrue(Decide.CMV2[1]);
    }

    /**
     *Tests LIC 1 with a sequence of NUMPOINTS planar points with a radius able to collect
     * a subsequence of three points in a circle of that radius.
     */
    @Test
    void condition1NotSatisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = rand.nextInt(60) + 40;
        Decide.X2 = new double[100];
        Decide.Y2 = new double[100];

        for(int i=0; i<100; i++){
            Decide.X2[i] = i;
            Decide.Y2[i] = i;
        }

        Decide.PARAMETERS2.RADIUS1 = 2;
        lic.condition1();

        assertFalse(Decide.CMV2[1]);
    }

    /**
     * Test LIC 0 with 3 points thats at least 1 length unit apart.
     * Tests if at least one set of two consecutive data points in are a distance greater than LENGTH1 apart.
     */
    @Test
    void condition0Test() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{-1,0,1};
        Decide.Y2 = new double[]{0,0,0};

        Decide.PARAMETERS2.LENGTH1 = 0.5;
        lic.condition0();
        assertEquals(true, Decide.CMV2[0], "Condition 0 should be satisfied");

        Decide.PARAMETERS2.LENGTH1 = 2;
        lic.condition0();
        assertEquals(false, Decide.CMV2[0], "Condition 0 should not be satisfied");


    }

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

        assertEquals(true, Decide.CMV2[2], "Condition 2 should be satisfied");
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
        assertFalse(Decide.CMV2[2], "Condition 2 should not be satisfied");
    }

    /**
     * Tests LIC 3 with only 3 points, that make a triangle with area = 4.5.
     * 4.5 > 3 and therefore satisfies LIC 3.
     */
    @Test
    void condition3Satisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 3;
        Decide.PARAMETERS2.AREA1 = 3;
        Decide.X2 = new double[]{1, 4, 3};
        Decide.Y2 = new double[]{1, 1, 4};

        lic.condition3();

        assertTrue(Decide.CMV2[3], "Condition 3 should be satisfied.");
    }

    /**
     * Tests LIC 3 with the last three points satisfying the condition.
     */
    @Test
    void condition3SatisfiedWithLast3(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 6;
        Decide.PARAMETERS2.AREA1 = 3;
        Decide.X2 = new double[]{1, 2, 3, 1, 4, 3};
        Decide.Y2 = new double[]{1, 2, 2, 1, 1, 4};

        lic.condition3();

        assertTrue(Decide.CMV2[3]);
    }
    /**
     * Tests LIC 3 with no consecutive points satisfying the condition.
     */
    @Test
    void condition3NotSatisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 6;
        Decide.PARAMETERS2.AREA1 = 3;
        Decide.X2 = new double[]{1, 2, 3, 1, 2, 3};
        Decide.Y2 = new double[]{1, 2, 2, 1, 1, 4};

        lic.condition3();

        assertFalse(Decide.CMV2[3], "Condition 3 should not be satisfied.");
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

    /**
     * Tests LIC 5 with 5 x-coordinates where some are followed by a smaller one.
     * 1 - 3 < 0 meaning it should be satisfied.
     */
    @Test
    void condition5Satisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{1,3,1,5,2};
        Decide.NUMPOINTS2 = 5;

        lic.condition5();

        assertTrue(Decide.CMV2[5], "Condition 5 should be satisfied");
    }

    /**
     * Tests LIC 5 with 5 x-coordinates where all are followed by a larger one.
     * X[i+1] - X[i] > 0 for all i meaning it should not be satisfied.
     */
    @Test
    void condition5NotSatisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{1,2,3,4,5};
        Decide.NUMPOINTS2 = 5;

        lic.condition5();

        assertFalse(Decide.CMV2[5], "Condition 5 should not be satisfied");
    }

    /**
     * Tests LIC 11 with 3 x-coordinates in which X[i]-X[j] < 0
     * LIC 11 should be satisfied.
     */
    @Test
    void condition11Satisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{5,0,0};
        Decide.PARAMETERS2.G_PTS = 1;
        Decide.NUMPOINTS2 = 3;

        lic.condition11();

        assertTrue(Decide.CMV2[11], "Condition 11 should be satisfied");
    }

    /**
     * Tests LIC 11 with 3 x-coordinates in which X[i]-X[j] > 0
     * LIC 11 should not be satisfied
     */
    @Test
    void condition11NotSatisfied() {
        LIC lic = new LIC();

        Decide.X2 = new double[]{-5, 0, 0};
        Decide.PARAMETERS2.G_PTS = 1;
        Decide.NUMPOINTS2 = 3;

        lic.condition11();

        assertFalse(Decide.CMV2[11], "Condition 11 should not be satisfied");
    }

    /**
     * Tests LIC 8 with three planar points with a maximum distance of 2 length units.
     * A radius of 2 should thus be able to include all points, not satisfying the condition.
     */
    @Test
    void condition8NotSatisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 5;

        Decide.X2 = new double[] {1, 2, 2, 3, 3};
        Decide.Y2 = new double[] {1, 2, 2, 3, 3};

        Decide.PARAMETERS2.A_PTS = 1;
        Decide.PARAMETERS2.B_PTS = 1;
        Decide.PARAMETERS2.RADIUS1 = 2;

        lic.condition8();

        assertFalse(Decide.CMV2[8], "Condition 8 should not be satisfied");
    }

    /**
     * Tests LIC 8 with three planar points with a distance of 4 length units.
     * A radius of 1.5 should thus not be able to include all points, satisfying the condition.
     */
    @Test
    void condition8Satisfied(){
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 5;

        Decide.X2 = new double[] {1, 2, 3, 3, 5};
        Decide.Y2 = new double[] {1, 2, 3, 3, 5};

        Decide.PARAMETERS2.A_PTS = 1;
        Decide.PARAMETERS2.B_PTS = 1;
        Decide.PARAMETERS2.RADIUS1 = 1.5;
      
        lic.condition8();

        assertTrue(Decide.CMV2[8], "Condition 8 should be satisfied");
    }
    /**
     * Tests LIC 10 with a triangle of size 9.5.
     * 9.5 > 9 for point indices 0, 2, and 4 meaning it is satisfied.
     */
    @Test
    void condition10Satisfied() {
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 5;
        Decide.X2 = new double[]{1,0,6,0,2};
        Decide.Y2 = new double[]{3,0,4,0,7};
        Decide.PARAMETERS2.E_PTS = 1;
        Decide.PARAMETERS2.F_PTS = 1;
        Decide.PARAMETERS2.AREA1 = 9;

        lic.condition10();

        assertTrue(Decide.CMV2[10], "Condition 10 should be satisfied");
    }

    /**
     * Tests LIC 10 with a triangle of size 1.
     * 1 < 10 for point indices 0, 2, and 4 meaning it is not satisfied.
     */
    @Test
    void condition10TooSmallTriangle() {
        LIC lic = new LIC();

        Decide.NUMPOINTS2 = 5;
        Decide.X2 = new double[]{3,0,2,0,3};
        Decide.Y2 = new double[]{3,0,2,0,1};
        Decide.PARAMETERS2.E_PTS = 1;
        Decide.PARAMETERS2.F_PTS = 1;
        Decide.PARAMETERS2.AREA1 = 10;

        lic.condition10();

        assertFalse(Decide.CMV2[10], "Condition 10 should not be satisfied");
    }
}