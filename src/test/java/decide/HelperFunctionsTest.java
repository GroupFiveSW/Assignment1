package decide;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HelperFunctionsTest {

    HelperFunctions H;

    double[] point1 = {-1,0};
    double[] point2 = {0,0};
    double[] point3 = {1,0};

    //Obtuse points, 135 degrees
    double[] point4 = {-1,1};
    double[] point5 = {0,0};
    double[] point6 = {1,0};

    //Accute points, 45 degrees
    double[] point7 = {1,1};
    double[] point8 = {0,0};
    double[] point9 = {1,0};


    //Large Circle
    double[] point10 = {10,10};
    double[] point11 = {0,0};
    double[] point12 = {9,0};

    double epsilon = Math.pow(10,-3);


    @BeforeEach
    void setUp() {
        H = new HelperFunctions();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAngleTestPositiveLargeAngle() {
        double[] point1 = {-1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};


        assertTrue((135-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 135+epsilon));

    }
    @Test
    void getAngleTestPositiveSmallAngle() {
        double[] point1 = {1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        assertTrue((45-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 45+epsilon));

    }

    @Test
    void getAngleTestNegative() {
        double[] point1 = {-1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        assertFalse((130-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 130+epsilon));

    }

    @Test
    void getAngleTestPointsOnLine() {
        assertTrue((180-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 180+epsilon));


    }

    @Test
    void euclideanDistanceTest() {
        double[] point1 = {0,0};
        double[] point2 = {3,4};
        assertEquals(5, H.euclideanDistance(point1,point2), "wrong distance calculated");
    }




    @Test
    void insideCircleTestOnALine() {
        // All points on a line
        double[][] points = {point1,point2,point3};
        assertTrue(H.insideCircle(2.0, points));
    }



    @Test
    void insideCircleObtuseTest() {
        // Obtuse angle between points
        double[][] points = {point4,point5,point6};
        assertTrue(H.insideCircle(1.5, points));
        assertFalse(H.insideCircle(1.0, points));

    }

    @Test
    void insideCircleAccuteTest() {
        double[][] points = {point7,point8,point9};
        assertTrue(H.insideCircle(0.75, points));
        assertFalse(H.insideCircle(0.5, points));
    }

    @Test
    void insideCircleFalseTest() {
        double[][] points = {point1,point2,point3};
        assertFalse(H.insideCircle(0.5, points));

    }

    @Test
    void insideCircleTestLargeCircleObtuseAngle() {
        double[][] points = {point10,point11,point12};
        assertTrue(H.insideCircle(7.5, points));
    }


    /**
     * Tests Wether the PUM generates a correct matrix with true inputs from CMV
     */
    @Test
    void PUMgeneratorTestTrueInputsFromCMV(){
        Decide.CONNECTORS[] conns = {Decide.CONNECTORS.ANDD, Decide.CONNECTORS.ORR, Decide.CONNECTORS.NOTUSED};
        for(int i = 0; i<15; i++){
            Decide.CMV2[i] = true;
            for(int j = i; j<15; j++){
                Decide.LCM2[i][j] = conns[(i+j)%3];
            }
        }
        H.PUMgenerator();
        for(int i = 0; i<15; i++){
            for(int j = i; j<15; j++){
                assertTrue(Decide.PUM2[i][j]);
                assertTrue(Decide.PUM2[j][i]);
            }
        }
    }

    /**
     * Tests Wether the PUM generates a correct matrix with false inputs from CMV
     */
    @Test
    void PUMgeneratorTestFalseInputsFromCMV(){
        Decide.CONNECTORS[] conns = {Decide.CONNECTORS.ANDD, Decide.CONNECTORS.ORR};
        for(int i = 0; i<15; i++){
            Decide.CMV2[i] = false;
            for(int j = i; j<15; j++){
                Decide.LCM2[i][j] = conns[(i+j)%2];
            }
        }
        H.PUMgenerator();
        for(int i = 0; i<15; i++){
            for(int j = i; j<15; j++){
                assertFalse(Decide.PUM2[i][j]);
                assertFalse(Decide.PUM2[j][i]);
            }
        }
    }

}