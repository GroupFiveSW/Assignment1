package decide;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HelperFunctionsTest {

    HelperFunctions H;

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

        double epsilon = Math.pow(10,-3);

        assertTrue((135-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 135+epsilon));

    }
    @Test
    void getAngleTestPositiveSmallAngle() {
        double[] point1 = {1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        double epsilon = Math.pow(10,-3);

        assertTrue((45-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 45+epsilon));

    }

    @Test
    void getAngleTestNegative() {
        double[] point1 = {-1,1};
        double[] point2 = {0,0};
        double[] point3 = {1,0};

        double epsilon = Math.pow(10,-3);

        assertFalse((130-epsilon<H.getAngle(point1,point2,point3)) && ( H.getAngle(point1,point2,point3) < 130+epsilon));

    }

    @Test
    void euclideanDistanceTest() {
        double[] point1 = {0,0};
        double[] point2 = {3,4};
        assertEquals(5, H.euclideanDistance(point1,point2), "wrong distance calculated");
    }



    @Test
    void insideCircle() {
    }

}