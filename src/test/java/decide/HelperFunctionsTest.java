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
    void getAngle() {
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