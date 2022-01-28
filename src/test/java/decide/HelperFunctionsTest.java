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

    //Obtuse points, 135 degrees
    double[] point4 = {-1,1};
    double[] point5 = {0,0};
    double[] point6 = {1,0};

    //Accute points, 45 degrees
    double[] point7 = {1,1};
    double[] point8 = {0,0};
    double[] point9 = {1,0};

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

}