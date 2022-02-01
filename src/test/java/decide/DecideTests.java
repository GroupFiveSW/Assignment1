package decide;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecideTests {


    /**
     * Test Decide with LIC 0,1 & 2 with three points.
     */
    @Test
    void launchTrueTest1(){

        //@TODO: Implement testing function once Decide-function is finished
        Decide.PARAMETERS2 = new Decide.PARAMETERS_T(
                3, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1);
        Decide.NUMPOINTS2 = 3;
        Decide.X2 = new double[]{0,0,4};
        Decide.Y2 = new double[]{4,0,0};
        Decide.PUV = new boolean[15];
        Decide.CONNECTORS[] conns = {Decide.CONNECTORS.ANDD, Decide.CONNECTORS.ORR, Decide.CONNECTORS.NOTUSED};
        for(int i = 0; i<15; i++){
            for(int j = i; j<15; j++){
                Decide.LCM2[i][j] = conns[0];
            }
        }
        Decide.decide();
        assertTrue(Decide.CMV2[0]);
        assertTrue(Decide.CMV2[1]);
        assertTrue(Decide.CMV2[2]);
        assertTrue(Decide.LAUNCH2);

    }


    /** Test Decide with LIC 0,1,2 being false but decide launches because PUV are all false */
    @Test
    void launchTrueTest2(){
        Decide.PARAMETERS2 = new Decide.PARAMETERS_T(
                8, 10, Decide.PI*0.51, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1);
        Decide.NUMPOINTS2 = 3;
        Decide.X2 = new double[]{0,0,4};
        Decide.Y2 = new double[]{4,0,0};
        Decide.PUV = new boolean[15];
        Decide.CONNECTORS[] conns = {Decide.CONNECTORS.ANDD, Decide.CONNECTORS.ORR, Decide.CONNECTORS.NOTUSED};
        for(int i = 0; i<15; i++){
            for(int j = i; j<15; j++){
                Decide.LCM2[i][j] = conns[0];
            }
        }
        Decide.decide();
        assertFalse(Decide.CMV2[0]);
        assertFalse(Decide.CMV2[1]);
        assertFalse(Decide.CMV2[2]);
        assertTrue(Decide.LAUNCH2);
    }


}
