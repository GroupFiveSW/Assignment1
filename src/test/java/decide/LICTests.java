package decide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LICTests {

    HelperFunctions helper = new HelperFunctions();

    Decide D = new Decide();
    Random rand = new Random();
    Decide.CONNECTORS orr = Decide.CONNECTORS.ORR;
    Decide.CONNECTORS andd = Decide.CONNECTORS.ANDD;
    Decide.CONNECTORS notused = Decide.CONNECTORS.NOTUSED;


    @BeforeEach
    void setUp(){
        for(int i=0; i<100; i++){

            D.X[i] = (rand.nextDouble()*200)-100;
            D.X2[i] = D.X[i];

            D.Y[i] = (rand.nextDouble()*200)-100;
            D.Y2[i] = D.Y[i];
        }

        D.NUMPOINTS = rand.nextInt(60) +40;


        for(int i = 0; i<15; i++){
            for(int j = i; j<15;j++){
                int r = rand.nextInt(2);
                if(r==0){
                    D.LCM[i][j] = orr;
                    D.LCM[j][i] = orr;
                }
                else if (r==1){
                    D.LCM[i][j] = andd;
                    D.LCM[j][i] = andd;
                }
                else if (r==2){
                    D.LCM[i][j] = notused;
                    D.LCM[j][i] = notused;
                }
            }
        }

    }

    /**
     *Tests LIC 1 with a sequence of three planar points with a radius unable to collect
     * all three points in a circle of that radius.
     */
    @Test
    void condition1Satisfied(){

        double[] point1 = {-10,10};
        double[] point2 = {0,0};
        double[] point3 = {10,0};
        double[][] triplet = {point1,point2,point3};
        assertTrue(!helper.insideCircle(10.0, triplet));
    }

    /**
     *Tests LIC 1 with a sequence of three planar points with a radius able to collect
     * all three points in a circle of that radius.
     */
    @Test
    void condition1NotSatisfied(){

        double[] point1 = {-10,10};
        double[] point2 = {0,0};
        double[] point3 = {10,0};
        double[][] triplet = {point1,point2,point3};
        assertFalse(!helper.insideCircle(20.0, triplet));
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
}