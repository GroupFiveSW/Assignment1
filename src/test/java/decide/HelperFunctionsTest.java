package decide;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HelperFunctionsTest {

    HelperFunctions H;

    double[] point1 = {-1,0};
    double[] point2 = {0,0};
    double[] point3 = {1,0};

    //Obtuse points, 135 degrees or 0.75*PI radians
    double[] point4 = {-1,1};
    double[] point5 = {0,0};
    double[] point6 = {1,0};

    //Accute points, 45 degrees or 0.25*PI radians
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


        assertTrue((0.75*Decide.PI-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 0.75*Decide.PI+epsilon));

    }
    @Test
    void getAngleTestPositiveSmallAngle() {
        double[] point1 = {1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        assertTrue((0.25*Decide.PI-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 0.25*Decide.PI+epsilon));

    }

    @Test
    void getAngleTestNegative() {
        double[] point1 = {-1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        assertFalse(((130*(Decide.PI/180))-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < (130*(Decide.PI/180))+epsilon));

    }

    @Test
    void getAngleTestPointsOnLine() {
        assertTrue((Decide.PI-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < Decide.PI+epsilon));


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

}