package decide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FUVGeneratorTest {

    boolean[][] filledPUM = new boolean[15][15];
    boolean[] filledPUV = new boolean[15];

    @BeforeEach
    public void setUp() {
        // Fill PUM with true and PUV with false.
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                this.filledPUM[i][j] = true;
            }
            this.filledPUV[i] = false;
        }
    }

    /**
     * Tests the generate method with:
     * PUV[2]=true and one element in PUM[2] are false, FUV[2] = false.
     * PUV[6]=true and all elements in PUM[6] are false, FUV[6] = false.
     * PUV[13]=true and all elements in PUM[13] are true, FUV[13] = true.
     */
    @Test
    void testGenerateVariousCases() {
        boolean[][] PUM = filledPUM;
        boolean[] PUV = filledPUV;

        boolean[] expected = new boolean[15];
        Arrays.fill(expected, true);

        PUV[2] = true;
        PUM[2][5] = false;
        expected[2] = false;

        PUV[6] = true;
        Arrays.fill(PUM[6], false);
        expected[6] = false;

        PUV[13] = true;

        Decide.PUM2 = PUM;
        Decide.PUV = PUV;
        FUVGenerator generator = new FUVGenerator();
        generator.generate();

        assertArrayEquals(expected, Decide.FUV2, "FUV has the wrong result");
    }

    /**
     * Tests the generate method with all PUV elements being false.
     * This should result in all FUV elements being true.
     */
    @Test
    void testGenerateAllPUVFalse() {
        boolean[][] PUM = filledPUM;
        boolean[] PUV = filledPUV;

        boolean[] expected = new boolean[15];
        Arrays.fill(expected, true);

        Decide.PUM2 = PUM;
        Decide.PUV = PUV;

        FUVGenerator generator = new FUVGenerator();
        generator.generate();

        assertArrayEquals(expected, Decide.FUV2, "All elements in FUV should be true");
    }
}