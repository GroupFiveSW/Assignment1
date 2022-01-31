package decide;

/**
 * A class that generates the FUV from the PUV and PUM
 */
public class FUVGenerator {

    /**
     * Generates the FUV from the PUV and PUM
     * according to the spec sections 2.3
     */
    public void generate() {
        boolean[] PUV = Decide.PUV;
        boolean[][] PUM = Decide.PUM2;

        boolean[] FUV = Decide.FUV2;

        for (int i = 0; i < 15; i++){
            if (PUV[i]) {
                // Find a false element in PUM row i.
                boolean foundFalseInPUM = false;
                for (int j = 0; j < 15; j++) {
                    if (!PUM[i][j]) {
                        foundFalseInPUM = true;
                        break;
                    }
                }
                FUV[i] = !foundFalseInPUM;
            } else {
                FUV[i] = true;
            }
        }
    }
}
